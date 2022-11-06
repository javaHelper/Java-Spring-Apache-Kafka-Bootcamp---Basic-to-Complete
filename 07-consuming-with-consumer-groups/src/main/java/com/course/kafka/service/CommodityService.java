package com.course.kafka.service;

import com.course.kafka.entity.Commodity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CommodityService {
    private static final Map<String, Commodity> COMMODITY_BASE = new HashMap<>();
    private static final String COPPER = "copper";
    private static final String GOLD = "gold";
    private static final double MAX_ADJUSTMENT = 1.05d;
    private static final double MIN_ADJUSTMENT = 0.95d;

    static {
        var timestamp = System.currentTimeMillis();

        COMMODITY_BASE.put(COPPER, new Commodity(COPPER, 5_900.57, "tonne", timestamp));
        COMMODITY_BASE.put(GOLD, new Commodity(GOLD, 1_895.29, "ounce", timestamp));
    }

    public List<Commodity> createDummyCommodities() {
        List<Commodity> commodities = new ArrayList<>();
        COMMODITY_BASE.keySet().forEach(s -> commodities.add(createDummyCommodity(s)));
        return commodities;
    }

    public Commodity createDummyCommodity(String name) {
        if (!COMMODITY_BASE.containsKey(name)) {
            throw new IllegalArgumentException("Invalid commodity : " + name);
        }

        var commodity = COMMODITY_BASE.get(name);
        var basePrice = commodity.getPrice();
        var newPrice = basePrice * ThreadLocalRandom.current().nextDouble(MIN_ADJUSTMENT, MAX_ADJUSTMENT);

        commodity.setPrice(newPrice);
        commodity.setTimestamp(System.currentTimeMillis());
        return commodity;
    }
}