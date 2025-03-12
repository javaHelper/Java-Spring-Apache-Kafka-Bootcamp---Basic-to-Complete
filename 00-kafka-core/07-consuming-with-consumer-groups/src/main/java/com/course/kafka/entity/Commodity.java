package com.course.kafka.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Commodity {
    private String name;
    private double price;
    private String measurement;
    private long timestamp;
}