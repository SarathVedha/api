package com.vedha.controller;

import com.vedha.dto.AllApiDTO;
import com.vedha.dto.ApiDTO;
import com.vedha.service.ApiService;
import com.vedha.util.ApiUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Apis-Gen", description = "Apis Generator")
public class ApiGenerator {

    private final ApiService apiService;

    @Operation(summary = "Add New Apis", description = "Add New Apis")
    @ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
    @PostMapping(value = "/v1/addApi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiDTO> addApis(@RequestParam("apiName") String apiName, @RequestParam(value = "apiResponseTimeSec", defaultValue = "1") String apiTime, @RequestBody HashMap<String, Object> apiValue) {

        ApiDTO build = ApiDTO.builder().apiName(apiName).apiResTime(apiTime).apiResponse(apiValue).build();

        return new ResponseEntity<>(apiService.insertNewApi(build), HttpStatus.CREATED);
    }

    @Operation(summary = "Get Apis", description = "Get Apis")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "/v1/getApi", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getApis(@RequestParam("apiName") String apiName) {

        return ResponseEntity.ok(apiService.getApi(apiName));
    }

    @Operation(summary = "Get Apis", description = "Get Apis")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @GetMapping(value = "/v1/getApi", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getApisGet(@RequestParam("apiName") String apiName, @RequestHeader HttpHeaders httpHeaders, HttpServletRequest httpServletRequest) {

        log.warn("request apiName : " + apiName);
        log.warn("request httpHeaders : " + httpHeaders);
        log.warn("client ip: " + ApiUtils.extractRemoteIpAddress(httpServletRequest));
        return ResponseEntity.ok(apiService.getApi(apiName));
    }

    @Operation(summary = "Get All Apis", description = "Get All Apis")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "/v1/getAllApi", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllApiDTO> getAllApis(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "3") int pageSize) {

        return ResponseEntity.ok(apiService.getAllApis(pageNumber, pageSize));
    }

    @Operation(summary = "Get Apis By Id", description = "Get Apis By Id")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "/v1/getApiById", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiDTO> getApiById(@RequestParam("id") int id) {

        return ResponseEntity.ok(apiService.getApiById(id));
    }

    @Operation(summary = "Get Apis By Name", description = "Get Apis By Name")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "/v1/getApiByName", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiDTO> getApiByName(@RequestParam("apiName") String apiName) {

        return ResponseEntity.ok(apiService.getApiByName(apiName));
    }

    @Operation(summary = "Update Apis By Id", description = "Update Apis By Id")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PutMapping(value = "/v1/updateApiById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiDTO> updateApiById(@RequestParam("id") int id, @RequestParam("apiName") String apiName, @RequestParam(value = "apiResponseTimeSec", defaultValue = "1") String apiTime, @RequestBody HashMap<String, Object> apiValue) {

        ApiDTO build = ApiDTO.builder().id(id).apiName(apiName).apiResTime(apiTime).apiResponse(apiValue).build();

        return ResponseEntity.ok(apiService.updateApiById(build));
    }

    @Operation(summary = "Update Apis By Name", description = "Update Apis By Name")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PutMapping(value = "/v1/updateApiByName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiDTO> updateApiByName(@RequestParam("apiName") String apiName, @RequestParam(value = "apiResponseTimeSec", defaultValue = "1") String apiTime, @RequestBody HashMap<String, Object> apiValue) {

        ApiDTO build = ApiDTO.builder().apiName(apiName).apiResTime(apiTime).apiResponse(apiValue).build();

        return ResponseEntity.ok(apiService.updateApiByName(build));
    }

    @Operation(summary = "Update All Apis Res Time", description = "Update All Apis Response Times")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 Ok")
    @PutMapping(value = "/v1/updateAllApiResTime", consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> updateAllApiResTime(@RequestParam(value = "apiResponseTimeSec", defaultValue = "0") String apiResponseTimeSec) {

        return ResponseEntity.ok(apiService.updateAllApisResTime(apiResponseTimeSec));
    }

    @Operation(summary = "Delete Apis By Id", description = "Delete Apis By Id")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @DeleteMapping(value = "/v1/deleteApiById", consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> deleteApiById(@RequestParam("id") int id) {

        return ResponseEntity.ok(apiService.deleteApiById(id));
    }

    @Operation(summary = "Delete Apis By Name", description = "Delete Apis By Name")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @DeleteMapping(value = "/v1/deleteApiByName", consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> deleteApiByName(@RequestParam("apiName") String apiName) {

        return ResponseEntity.ok(apiService.deleteApiByName(apiName));
    }
}
