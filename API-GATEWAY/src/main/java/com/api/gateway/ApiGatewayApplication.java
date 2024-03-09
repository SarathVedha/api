package com.api.gateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Apis Gateway", summary = "Apis Gateway",
		version = "V1.0", description = "Documentation of Apis Gateway",
		contact = @Contact(name = "Sarath", email = "sarath.perumal@intellectdesign.com"),
		license = @License(name = "@VedhaGroups")))
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
