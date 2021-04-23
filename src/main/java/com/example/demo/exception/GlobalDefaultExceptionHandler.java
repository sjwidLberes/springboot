package com.example.demo.exception;

import com.example.demo.enums.ExceptionEnum;
import com.example.demo.vo.result.JsonResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalDefaultExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler
    public JsonResultVo exceptionHandler(Exception e){
        e.printStackTrace();
        logger.error("exceptionHandler: " + e.getMessage());

        if(e instanceof ParamException){
            ParamException paramException=(ParamException)e;
            return JsonResultVo.buildErrorVo(paramException.getCode(),paramException.getMessage());
        }else if(e instanceof ServerException){
            ServerException serverException=(ServerException)e;
            return JsonResultVo.buildErrorVo(serverException.getCode(),serverException.getMessage());
        }else{
            return JsonResultVo.buildErrorVo(ExceptionEnum.SERVER_ERROR.getCode(),e.getMessage());
        }
    }
}
