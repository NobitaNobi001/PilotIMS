package com.pilot.boot.interceptor;

import com.pilot.boot.exception.Assert;
import com.pilot.boot.utils.CommonResult;
import com.pilot.boot.utils.ConstantUtil;
import com.pilot.boot.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
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
        if (ConstantUtil.OPTIONS.toString().equals(request.getMethod())){
            return true;
        }
        //2.get userId & token
        String userId = request.getHeader("userId");
        String clientToken = request.getHeader("token");

        //get redis token by userId
        String token = (String) redisUtil.get(userId);

        //3.check
        Assert.notEmpty(clientToken, CommonResult.fail(300,"登录信息已失效,请重新登录"));
        Assert.isEqual(clientToken,token,CommonResult.fail(300,"登录信息已失效,请重新登录"));

        return true;
    }
}
