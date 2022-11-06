package com.course.kafka.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.kafka.entity.Commodity;
import com.course.kafka.service.CommodityService;

@RestController
@RequestMapping("/api/commodity/v1")
public class CommodityApi {

    @Autowired
    private CommodityService commodityService;

    @GetMapping(value = "/all")
    public List<Commodity> generateAllCommodities() {
        return commodityService.createDummyCommodities();
    }
}