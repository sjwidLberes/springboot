package com.example.demo.controllers;

import com.example.demo.enums.ExceptionEnum;
import com.example.demo.exception.ParamException;
import com.example.demo.exception.ServerException;
import com.example.demo.service.UserService;
import com.example.demo.vo.request.UserRequestVo;
import com.example.demo.vo.result.JsonResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by admin on 2017/7/9.
 */
public class BaseController {

    @ExceptionHandler
    @ResponseBody
    public JsonResultVo exceptionHandler(Exception e){
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
