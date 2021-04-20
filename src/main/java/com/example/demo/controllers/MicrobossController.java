package com.example.demo.controllers;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.entity.Microboss;
import com.example.demo.service.MicrobossService;
import com.example.demo.vo.result.JsonResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/microboss")
public class MicrobossController {
    private static Logger logger = LoggerFactory.getLogger(MicrobossController.class);


    @Autowired
    private MicrobossService microbossService;

    @RequestMapping(value = "/get/{id}")
    public JsonResultVo get(@PathVariable int id){
        Microboss microboss = microbossService.findById(id);
        return JsonResultVo.buildResultVo(microboss);
    }

    @RequestMapping(value = "/get/byPage/{page}/{size}")
    public JsonResultVo byPage(@PathVariable int page, @PathVariable int size, @RequestParam int validationState){
        Page<Microboss> microbosses = microbossService.getAllByValidateState(validationState, page, size);

        return JsonResultVo.buildResultVo(microbosses);
    }

    @RequestMapping(value = "/{id}/validationState/update", method = RequestMethod.POST)
    public JsonResultVo validationSateUpdate(@PathVariable int id, @RequestBody JSONObject requestBody){

        microbossService.updateValidationStateById(id, requestBody.getIntValue("validationState"), requestBody.getString("message"));

        return JsonResultVo.buildResultVo(null);
    }


}
