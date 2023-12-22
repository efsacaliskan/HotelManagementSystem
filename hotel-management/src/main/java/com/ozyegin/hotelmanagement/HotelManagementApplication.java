package com.ozyegin.hotelmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@ComponentScan(basePackages = "com.ozyegin.hotelmanagement")
@OpenAPIDefinition(info = @Info(version = "1.0.0", 
title = "Hotel Management System API", 
description = "This is a simple API",
contact = @Contact(email="efsa.caliskan@ozu.edu.tr", name = "Efsa Caliskan")))
public class HotelManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementApplication.class, args);
	}

}
