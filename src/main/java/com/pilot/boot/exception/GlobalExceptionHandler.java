package com.pilot.boot.exception;

import com.pilot.boot.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.omg.IOP.CodecPackage.TypeMismatch;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * global exception handler
 *
 * @author ezuy
 * @date 20/12/22 16:22
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.pilot.boot")
public class GlobalExceptionHandler {

    /**
     * handler Exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public CommonResult handlerException(Exception e) {
        return new CommonResult(100, e.getMessage());
    }



    /**
     * handler myException
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MyException.class})
    public CommonResult handlerMyException(MyException e) {
        log.error(e.getMessage(), e);
        return new CommonResult(e.getCode(),e.getMessage());
    }

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
     * 处理controller上层异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatch.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class,
            MethodArgumentTypeMismatchException.class
    })
    public CommonResult handleServletException(Exception e) {
        log.error(e.getMessage(), e);
        return CommonResult.fail(100, "servlet请求异常," + e.getMessage());
    }

}
