package com.vedha.controller;

import com.vedha.service.DateConvertor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/date")
@RequiredArgsConstructor
@Tag(name = "Date-Convertor", description = "Date Convertor")
public class DateApiController {

    private final DateConvertor dateConvertor;

    @Operation(summary = "convertGregoToHijri", description = "Convert Gregorian To Hijri")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "v1/GregoToHijri", consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> convertGregoToHijri(@RequestParam(value = "gregorianDate", defaultValue = "dd/MM/yyyy") String gregorianDate) {

        return ResponseEntity.ok(dateConvertor.convertGregorianToHijri(gregorianDate));
    }

    @Operation(summary = "convertHijriToGrego", description = "Convert Hijri To Gregorian")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "v1/HijriToGrego", consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> convertHijriToGrego(@RequestParam(value = "hijriDate", defaultValue = "dd/MM/yyyy") String hijriDate) {

        return ResponseEntity.ok(dateConvertor.convertHijriToGregorian(hijriDate));
    }
}
