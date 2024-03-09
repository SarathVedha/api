package com.vedha.config;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.vedha.entity.ApiEntity;
import com.vedha.repository.ApiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Configuration
@Profile("local")
@RequiredArgsConstructor
public class AppRunnerLocal implements CommandLineRunner {

    private final Gson gson;

    private final ApiRepository apiRepository;

    private final ResourceLoader resourceLoader;

    @Value("${stub.api.delay}")
    public String apiDelay;

    @Value("${server.port}")
    public String appPort;

    @Override
    public void run(String... args) throws Exception {

        log.info("Runner Api-Loader Started");

        String path = new ClassPathResource("/apis/").getURI().getPath();
        List<ApiEntity> collect;

        if (StringUtils.hasText(path)) {

            log.info("Build In APIs Path: " + path);
            List<File> list = Arrays.stream(Objects.requireNonNull(new File(path).listFiles())).toList();
            log.info("Total Apis Found To Load: " + (list.size()));
            log.info("Setting Default Delay Time: " + apiDelay.concat("s"));

            collect = list.stream().filter(file -> !file.getName().equalsIgnoreCase("APIS.TXT")).map(file -> {
                try {
                    return ApiEntity.builder()
                            .apiName(file.getName().toUpperCase())
                            .apiResTime(apiDelay)
                            .apiResponse(gson.toJson(JsonParser.parseString(Files.readString(Path.of(file.getPath()), StandardCharsets.UTF_8)))).
                            build();
                } catch (Exception e) {
                    throw new RuntimeException("Exception While Parsing File ".concat(file.getName()).concat(" : " + e));
                }
            }).toList();

        }else {

            // Added For Reading in Jar
            log.info("Build In APIs Path: " + resourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX.concat("apis/APIs.txt")).getURL());
            collect = AppRunnerProd.getApiEntities(resourceLoader, log, apiDelay, gson);
        }

        log.info("Total Apis Loaded: ".concat(String.valueOf(apiRepository.saveAll(collect).size())));

        log.info("Runner Api-Loader Completed");

        log.info("Application Ready To Serve At: ".concat("https://localhost:".concat(appPort)));
    }
}
