package com.vedha.config;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.vedha.entity.ApiEntity;
import com.vedha.repository.ApiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Configuration
@Profile("prod")
@RequiredArgsConstructor
public class AppRunnerProd implements CommandLineRunner {

    private final Gson gson;

    private final ApiRepository apiRepository;

    private final ResourceLoader resourceLoader;

    @Value("${stub.api.path}")
    public String apiPath;

    @Value("${stub.api.delay}")
    public String apiDelay;

    @Value("${server.port}")
    public String appPort;

    @Override
    public void run(String... args) throws Exception {

        log.info("Runner Api-Loader Started");
        List<ApiEntity> collect;

        if (StringUtils.hasText(apiPath)){

            log.info("Building In Custom APIs Path: " + apiPath);
            List<File> list = Arrays.stream(Objects.requireNonNull(new File(apiPath).listFiles())).toList();
            log.info("Total Apis Found To Load: " + (list.size()));
            log.info("Setting Default Delay Time: " + apiDelay.concat("s"));

            collect = list.stream().filter(file -> !file.getName().equalsIgnoreCase("APIS.TXT")).map(file -> {
                try {
                    return ApiEntity.builder()
                            .apiName(file.getName().toUpperCase())
                            .apiResTime(apiDelay)
                            .apiResponse(gson.toJson(JsonParser.parseString(Files.readString(Path.of(file.getPath()), StandardCharsets.UTF_8))))
                            .build();
                } catch (Exception e) {
                    throw new RuntimeException("Exception While Parsing File ".concat(file.getName().toUpperCase()).concat(" : " + e));
                }
            }).toList();

        }else {

            // Added For Reading in Jar
            log.info("Taking Build In APIs Path: " + resourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX.concat("apis/APIs.txt")).getURL());
            collect = getApiEntities(resourceLoader, log, apiDelay, gson);
        }

        log.info("Total Apis Loaded: ".concat(String.valueOf(apiRepository.saveAll(collect).size())));

        log.info("Runner Api-Loader Completed");

        log.info("Application Ready To Serve At: ".concat("http://localhost:".concat(appPort)));
    }

    static List<ApiEntity> getApiEntities(ResourceLoader resourceLoader, Logger log, String apiDelay, Gson gson) throws IOException {
        List<ApiEntity> collect;
        List<String> listFiles = IOUtils.readLines(resourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX.concat("apis/APIs.txt")).getInputStream(), StandardCharsets.UTF_8);
        log.info("Total Apis Found To Load: " + (listFiles.size()));
        log.info("Setting Default Delay Time: " + apiDelay.concat("s"));

        collect = listFiles.stream().filter(file -> !file.equalsIgnoreCase("APIS.TXT")).map(file -> {
            try {
                return ApiEntity.builder()
                        .apiName(file.toUpperCase())
                        .apiResTime(apiDelay)
                        .apiResponse(gson.toJson(JsonParser.parseString(IOUtils.toString(resourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX.concat("apis/").concat(file)).getInputStream(), StandardCharsets.UTF_8))))
                        .build();
            } catch (Exception e) {
                throw new RuntimeException("Exception While Parsing File ".concat(file).concat(" : " + e));
            }
        }).toList();
        return collect;
    }
}
