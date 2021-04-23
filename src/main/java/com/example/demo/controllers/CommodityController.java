package com.example.demo.controllers;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.entity.Commodity;
import com.example.demo.dao.entity.CommodityType;
import com.example.demo.service.AliYunOssService;
import com.example.demo.service.CommodityService;
import com.example.demo.vo.result.JsonResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping(value = "/commodity")
public class CommodityController {
    private static Logger logger = LoggerFactory.getLogger(CommodityController.class);

    @Autowired
    private AliYunOssService aliYunOssService;
    @Autowired
    private CommodityService commodityService;

    //分页 名称 状态
    @RequestMapping(value = "/get/byPage/{page}/{size}")
    public JsonResultVo byPage(@PathVariable int page, @PathVariable int size, @RequestParam String name, @RequestParam int onShelf){

        Page<Commodity> commodities = commodityService.getAllByNameAndOnShelf(name, onShelf, page, size);

        return JsonResultVo.buildResultVo(commodities);
    }

    @RequestMapping(value = "/get/byId/{id}")
    public JsonResultVo get(@PathVariable int id) {
        logger.info("get-------------------------------");
        logger.info("id: " + id);

        return JsonResultVo.buildResultVo(commodityService.findById(id));
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public JsonResultVo onShelfUpdate(@PathVariable int id, @RequestBody JSONObject requestBody){
        logger.info("update-------------------------------");

        commodityService.updateOnShelfById(id, requestBody.getBoolean("onShelf"));

        return JsonResultVo.buildSuccessVo();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonResultVo save(
            @RequestParam int id,
            @RequestParam int type,
            @RequestParam String name,
            @RequestParam int thumb_img_num,
            @RequestParam int detail_img_num,
            @RequestParam int intro_img_num,
            @RequestParam int share_img_num,
            @RequestParam BigDecimal price,
            @RequestParam BigDecimal promotional_price,
            @RequestParam int minimum_purchase_quantity,
            @RequestParam boolean on_shelf,
            StandardMultipartHttpServletRequest request) {
        logger.info("save-------------------------------");

        Commodity commodity = new Commodity();
        if (id != -1) commodity.setId(id);
        commodity.setName(name);
        CommodityType commodityType = new CommodityType();
        commodityType.setId(type);
        commodity.setType(commodityType);
        commodity.setMinimumPurchaseQuantity(minimum_purchase_quantity);
        commodity.setPrice(price);
        commodity.setPromotionalPrice(promotional_price);
        commodity.setOnShelf(on_shelf);
        commodity.setTimestamp(new Date());

        Map parameters = request.getParameterMap();
        MultiValueMap<String, MultipartFile> multipartFiles = request.getMultiFileMap();

        commodity = commodityService.saveCommodity(commodity, parameters, multipartFiles, thumb_img_num, detail_img_num, intro_img_num, share_img_num);
        return JsonResultVo.buildResultVo(commodity);
    }

}
