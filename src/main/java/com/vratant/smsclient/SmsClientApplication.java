package com.vratant.smsclient;

import com.vratant.smsclient.mapclassjson.StudentDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@SpringBootApplication
public class SmsClientApplication {

	private static final Logger log =  LoggerFactory.getLogger(SmsClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SmsClientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			StudentDetails studentDetails = restTemplate.getForObject(
					"http://localhost:8080/students/2", StudentDetails.class);
			log.info(studentDetails.toString());

			StudentDetails studentDetails1 = restTemplate.getForObject(
					"http://localhost:8080/students/2", StudentDetails.class);
			log.info(studentDetails1.toString());
//
//			will create new records
//			studentDetails1.setName("shantam");
//			restTemplate.postForLocation("http://localhost:8080/students",studentDetails1);

			StudentDetails studentDetails2 = restTemplate.getForObject(
					"http://localhost:8080/students/6", StudentDetails.class);
			System.out.println(studentDetails2.toString());

			StudentDetails studentDetailsPut = studentDetails2;
			studentDetailsPut.setName("updatedShantam");

//			restTemplate.put("http://localhost:8080/students/7",studentDetailsPut);
//
//			restTemplate.delete("http://localhost:8080/students/7");

		};
	}
}
