package com.pilot.boot.interceptor;

import com.pilot.boot.exception.IdentifyException;
import com.pilot.boot.utils.RedisUtil;
import com.pilot.boot.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * request interceptor
 *
 * @author ezuy
 * @date 21/1/27 15:01
 */
@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * check user login
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        //1. Sniffing request
        //2.get userId & token
        String userId = request.getHeader("userId");
        String clientToken = request.getHeader("token");

        //get redis token by userId
        String token = (String) redisUtil.get(userId);

        //3.check
        if (StringUtils.isEmpty(clientToken)) {
            throw new IdentifyException("登录信息已失效,请重新登录");
        }

        if (!clientToken.equals(token)) {
            throw new IdentifyException("登录信息已失效,请重新登录");
        }

        return true;
    }
}
