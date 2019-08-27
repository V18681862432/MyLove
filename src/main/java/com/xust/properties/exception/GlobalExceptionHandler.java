package com.xust.properties.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Victor
 * @create: 2019-08-15 20:05
 **/
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler( Exception e){

        if( e instanceof BindException){
            BindException be = (BindException) e;
            List<ObjectError> errors = be.getAllErrors();
            Object error = errors.get(0);
            return CodeMsg.BIND_ERROR.fillArgs(error).getMsg();
        }else {
            e.printStackTrace();
            return e.toString()+CodeMsg.SERVER_ERROR;
        }
    }
}
