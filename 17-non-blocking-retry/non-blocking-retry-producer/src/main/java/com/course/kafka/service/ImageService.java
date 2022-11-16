package com.course.kafka.service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.course.kafka.entity.Image;

@Service
public class ImageService {

	private static AtomicInteger counter = new AtomicInteger();
	
	public Image generateImage(String type) {
		var name = "image-" + counter.incrementAndGet();
		var size = ThreadLocalRandom.current().nextLong(100, 10_000);
		
		return new Image(name, size, type);
	}
	
}
