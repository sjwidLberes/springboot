package com.example.demo.controllers;

import com.example.demo.enums.ExceptionEnum;
import com.example.demo.exception.ParamException;
import com.example.demo.service.UserService;
import com.example.demo.vo.request.UserRequestVo;
import com.example.demo.vo.result.JsonResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by admin on 2017/7/9.
 */
@RestController
@RequestMapping(path = "/demo/user/")
public class UserController extends BaseController{

    @Autowired
    UserService userService;

    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public JsonResultVo save(@Valid UserRequestVo userRequestVo, BindingResult result){
        userService.save(userRequestVo);
        return JsonResultVo.buildSuccessVo();
    }

    @RequestMapping(value = "test",method = RequestMethod.GET)
    @ResponseBody
    public void test(){
        throw new ParamException(ExceptionEnum.DB_ERROR);
    }
}
