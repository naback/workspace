package com.teste.teste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.teste")
@EntityScan( basePackages = {"com.teste.to"})
public class TesteApplication
{

	public static void main(String[] args)
	{
		System.setProperty("server.servlet.context-path", "/teste");
		SpringApplication.run(TesteApplication.class, args);
	}

}
