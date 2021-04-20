package com.example.demo.controllers;

import com.example.demo.dao.entity.Oder;
import com.example.demo.service.OrderService;
import com.example.demo.vo.result.JsonResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/get/byPage/{page}/{size}")
    public JsonResultVo byPage(@PathVariable int page, @PathVariable int size, @RequestParam String id, @RequestParam int type, @RequestParam String startDate, @RequestParam String endDate, @RequestParam int allAccepted){
        Page<Oder> oders = orderService.getAll(page, size, id, type, startDate, endDate, allAccepted);


        return JsonResultVo.buildResultVo(oders);
    }
}
