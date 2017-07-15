package com.hclfintech.management.assets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.hclfintech.management" })
public class AssetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetsApplication.class, args);
	}
}
