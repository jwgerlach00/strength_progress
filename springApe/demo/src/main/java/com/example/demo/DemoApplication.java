package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println(Calculator.goalProgressionTimeline(
            225,
            1,
            225f+28.5f,
            "aggressive"
        ));
	}

}
