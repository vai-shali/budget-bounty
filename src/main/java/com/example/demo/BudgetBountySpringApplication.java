package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import static com.example.demo.ConsoleHandler.MainMenu.mainMenu;
import static com.example.demo.ConsoleHandler.Payment.processPayment;

import java.time.LocalDate;

@SpringBootApplication
public class BudgetBountySpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetBountySpringApplication.class, args);
//        mainMenu();
		processPayment();

    }


}
