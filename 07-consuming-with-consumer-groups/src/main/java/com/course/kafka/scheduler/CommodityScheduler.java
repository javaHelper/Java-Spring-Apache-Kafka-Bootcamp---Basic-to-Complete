package com.course.kafka.scheduler;

import com.course.kafka.entity.Commodity;
import com.course.kafka.producer.CommodityProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommodityScheduler {
    public static final String API = "http://localhost:8080/api/commodity/v1/all";
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CommodityProducer producer;

    @Scheduled(fixedRate = 5000)
    public void fetchCommodities(){
        List<Commodity> commodities = restTemplate.exchange(API, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Commodity>>() {
                }).getBody();

        commodities.forEach(commodity -> {
            try {
                producer.sendMessage(commodity);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}