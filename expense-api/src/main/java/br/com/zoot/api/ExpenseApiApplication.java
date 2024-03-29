package br.com.zoot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ExpenseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseApiApplication.class, args);
	}

}
