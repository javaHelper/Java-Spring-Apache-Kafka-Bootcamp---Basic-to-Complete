package com.course.kafka;

import com.course.kafka.entity.Employee;
import com.course.kafka.producer.Employee2JsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private Employee2JsonProducer producer;

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 5; i++) {
			Employee employee = new Employee("emp-" + i, "Employee " + i, LocalDate.now());
			producer.send(employee);
		}
	}
}