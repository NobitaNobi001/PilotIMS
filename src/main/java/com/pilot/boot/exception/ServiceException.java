package com.pilot.boot.exception;

/**
 * @author ezuy
 * @date 21/3/2 13:58
 */
public class ServiceException extends RuntimeException{

    public ServiceException(String message){
        super(message);
    }
}
