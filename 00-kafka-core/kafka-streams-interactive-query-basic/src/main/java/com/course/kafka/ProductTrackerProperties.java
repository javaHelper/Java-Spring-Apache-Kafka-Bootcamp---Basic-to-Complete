package com.course.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ConfigurationProperties(prefix = "app.product.tracker")
public class ProductTrackerProperties {
	private String productIds;
}
