package com.example.demo.vo.result;

import com.example.demo.enums.ExceptionEnum;

/**
 * Created by admin on 2017/7/23.
 */
public class JsonResultVo {
    public static final int SUCCESS=200;

    protected int status=SUCCESS;

    protected String message;

    protected Object data;

    public JsonResultVo(ExceptionEnum exceptionEnum){
        this.status=exceptionEnum.getCode();
        this.message=exceptionEnum.getMessage();
    }

    public JsonResultVo(){

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static JsonResultVo buildSuccessVo(){
        JsonResultVo vo=new JsonResultVo();
        vo.setStatus(SUCCESS);
        vo.setMessage(ExceptionEnum.SUCCESS.getMessage());
        return vo;
    }

    public static JsonResultVo buildSuccessVo(String message){
        JsonResultVo vo=new JsonResultVo();
        vo.setStatus(SUCCESS);
        vo.setMessage(message);
        return vo;
    }

    public static JsonResultVo buildResultVo(Object data){
        JsonResultVo vo=buildSuccessVo();
        vo.setStatus(SUCCESS);
        vo.setData(data);
        return vo;
    }


    public static JsonResultVo buildErrorVo(int status, String message){
        JsonResultVo vo=new JsonResultVo();
        vo.setStatus(status);
        vo.setMessage(message);
        return vo;
    }
}
