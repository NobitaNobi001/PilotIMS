package com.pilot.boot.exception;

import com.pilot.boot.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.ws.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * global exception handler
 *
 * @author ezuy
 * @date 20/12/22 16:22
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.pilot.boot.controller")
public class GlobalExceptionHandler {

    /**
     * JR303 argument bind exception
     *
     * @param e exception
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public CommonResult handlerValidateException(Exception e) {

        //1.declare
        BindingResult bindingResult = null;

        //2.find which exception
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindingException) {
            bindingResult = ((BindException) e).getBindingResult();
        }

        //3.declare a map to save errors
        Map<String, String> errorMap = new HashMap<>(16);

        //4.forEach error to save
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        //5.response to front
        return new CommonResult(100, "参数格式错误!", errorMap);
    }

    /**
     * handler Exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public CommonResult handlerMyException(Exception e) {
        return new CommonResult(100, "");
    }


    /**
     * handler dao exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DaoException.class)
    public CommonResult handlerDaoException(Exception e) {
        return CommonResult.fail(100, "失败");
    }

    /**
     * handler service exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public CommonResult handlerServiceException(Exception e) {
        return CommonResult.fail(100, "失败");
    }

    /**
     * handler IdentifyException
     *
     * @param e
     * @return
     */
    @ExceptionHandler({IdentifyException.class})
    public CommonResult handlerIdentifyException(Exception e) {
        return new CommonResult(300, e.getMessage());
    }
}
