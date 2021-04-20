package com.example.demo.controllers;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.entity.Welfare;
import com.example.demo.service.WelfareService;
import com.example.demo.vo.result.JsonResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/welfare")
public class WelfareController {
    private static Logger logger = LoggerFactory.getLogger(WelfareController.class);

    @Autowired
    private WelfareService welfareService;

    @RequestMapping(value = "/expressNumber/update", method = RequestMethod.POST)
    public JsonResultVo expressNumberUpdate(@RequestBody JSONObject requestBody){
        welfareService.updateExpressNumberByKey(requestBody.getString("orderId"), requestBody.getString("openId"), requestBody.getString("expressNumber"));

        return JsonResultVo.buildSuccessVo();
    }

    @RequestMapping(value = "/get/{orderId}/byPage/{page}/{size}")
    public JsonResultVo byPage(@PathVariable String orderId, @PathVariable int page, @PathVariable int size){

        Page<Welfare> welfares = welfareService.getAllByOrderId(orderId, page, size);

        return JsonResultVo.buildResultVo(welfares);
    }

}
