package com.yahaha.reggie.common;

/**
 * @author Yahaha
 * @date 2022-08-23 09:59:35
 * @description 自定义业务异常
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
