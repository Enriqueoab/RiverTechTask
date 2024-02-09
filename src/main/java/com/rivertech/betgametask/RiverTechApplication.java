package com.rivertech.betgametask;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RiverTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiverTechApplication.class, args);
	}

}
