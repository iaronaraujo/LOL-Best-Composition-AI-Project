package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.controllers.GeneticAlgorithmController;

@SpringBootApplication
@ComponentScan(basePackageClasses = GeneticAlgorithmController.class)
public class LolBestCompositionAiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LolBestCompositionAiProjectApplication.class, args);
	}
}
