package com.course.kafka;

import com.course.kafka.entity.Employee;
import com.course.kafka.producer.EmployeeJsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ProducingJsonMessageApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProducingJsonMessageApplication.class, args);
	}

	@Autowired
	private EmployeeJsonProducer producer;


	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 5; i++) {
			Employee employee = Employee.builder()
					.employeeId("emp-" + i)
					.name("Employee " + i)
					.birthDate(LocalDate.now()).build();
			producer.sendMessage(employee);
		}
	}
}