package com.imagined;
import java.util.Arrays;

import com.imagined.s3.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImaginedApplication {
	public static void main(String[] args) {
		SpringApplication.run(ImaginedApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx, S3Service s3Service) {
		return args -> {

//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}

			s3Service.putObject(
					"imagined-storage",
					"testing",
					"Hello World".getBytes()
			);

			byte[] testing = s3Service.getObject(
					"imagined-storage",
					"testing"
			);

			System.out.println("Got the object " + new String(testing));
		};
	}

}

