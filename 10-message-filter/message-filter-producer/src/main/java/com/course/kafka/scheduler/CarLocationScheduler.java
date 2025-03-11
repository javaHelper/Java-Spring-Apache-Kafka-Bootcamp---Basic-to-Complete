package com.course.kafka.scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.course.kafka.entity.CarLocation;
import com.course.kafka.producer.CarLocationProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class CarLocationScheduler {

	private static final Logger LOG = LoggerFactory.getLogger(CarLocationScheduler.class);

	private CarLocation carOne;
	private CarLocation carTwo;
	private CarLocation carThree;

	@Autowired
	private CarLocationProducer producer;

	public CarLocationScheduler() {
		var now = System.currentTimeMillis();

		carOne = new CarLocation("car-one", now, 0);
		carTwo = new CarLocation("car-two", now, 110);
		carThree = new CarLocation("car-three", now, 95);
	}

	@Scheduled(fixedRate = 10000)
	public void generateCarLocation() throws JsonProcessingException {
		var now = System.currentTimeMillis();
		
		carOne.setTimestamp(now);
		carTwo.setTimestamp(now);
		carThree.setTimestamp(now);

		carOne.setDistance(carOne.getDistance() + 1);
		carTwo.setDistance(carTwo.getDistance() - 1);
		carThree.setDistance(carThree.getDistance() + 1);
		
		producer.send(carOne);
		producer.send(carTwo);
		producer.send(carThree);
		
		LOG.info("Sent : {}", carOne);
		LOG.info("Sent : {}", carTwo);
		LOG.info("Sent : {}", carThree);
	}
}