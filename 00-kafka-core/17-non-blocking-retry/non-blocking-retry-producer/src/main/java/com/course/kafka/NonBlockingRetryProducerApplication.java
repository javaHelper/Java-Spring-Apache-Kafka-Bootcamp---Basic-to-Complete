package com.course.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.entity.Image;
import com.course.kafka.producer.Image2Producer;
import com.course.kafka.service.ImageService;

@SpringBootApplication
public class NonBlockingRetryProducerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(NonBlockingRetryProducerApplication.class, args);
	}

	@Autowired
	private ImageService imageService;
	@Autowired
	private Image2Producer imageProducer;

	@Override
	public void run(String... args) throws Exception {
		Image img1 = imageService.generateImage("jpg");
		Image img2 = imageService.generateImage("svg");
		Image img3 = imageService.generateImage("png");
		Image img4 = imageService.generateImage("gif");
		Image img5 = imageService.generateImage("bmp");
		Image img6 = imageService.generateImage("tiff");

		imageProducer.send(img1, 0);
		imageProducer.send(img2, 0);
		imageProducer.send(img3, 0);

		imageProducer.send(img4, 1);
		imageProducer.send(img5, 1);
		imageProducer.send(img6, 1);
	}

}
