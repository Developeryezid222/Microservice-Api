package com.microservice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//Habilita la comunicación entre microservicios dinámicamente.
//es un sistema que actúa como intermediario entre los clientes y los servicios de backend.
//proporcionando un punto de entrada único para acceder a múltiples APIs.
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceGatewayApplication.class, args);
	}

}
