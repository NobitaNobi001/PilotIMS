package com.pilot.boot.exception;

import com.pilot.boot.utils.CommonResult;
import lombok.Data;

/**
 * my Exception
 * @author ezuy
 * @date 21/1/21 14:11
 */
@Data
public class MyException extends RuntimeException{

    private Integer code;

    private String message;

    public MyException(String message) {
        super(message);
    }

    public MyException(CommonResult commonResult){
        this.code = commonResult.getCode();
        this.message = commonResult.getMessage();
    }
}
