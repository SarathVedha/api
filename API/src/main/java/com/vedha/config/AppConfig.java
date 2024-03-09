package com.vedha.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Slf4j
@Configuration
@OpenAPIDefinition(info = @Info(title = "Apis Generator", summary = "Apis Generator",
        version = "V1.0", description = "User Apis Generator",
        contact = @Contact(name = "Sarath", email = "sarath.perumal@intellectdesign.com"),
        license = @License(name = "@VedhaGroups")))
public class AppConfig {

    @Value("${openai.api-url}")
    private String openApiUrl;

    @Value("${openai.api-key}")
    private String openApiKey;

    @Bean
    public ModelMapper initilizeModelMapper() {

        return new ModelMapper();
    }

    @Bean
    public Gson initilizeGson() {

        return new GsonBuilder().setPrettyPrinting().create();
    }

    @Bean
    public RestClient initilaizeRestClient() {

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(openApiUrl));

        restTemplate.getInterceptors().add((request, body, execution) -> {
           request.getHeaders().add("Authorization", "Bearer ".concat(openApiKey));
           return execution.execute(request, body);
        });

        return RestClient.create(restTemplate);
    }
}
