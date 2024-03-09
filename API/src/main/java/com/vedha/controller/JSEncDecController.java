package com.vedha.controller;

import com.vedha.service.JavaScriptSalt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/js")
@RequiredArgsConstructor
@Tag(name = "JS-Encrypt-Decrypt", description = "JavaScript Encrypt & Decrypt")
public class JSEncDecController {

    private final JavaScriptSalt javaScriptSalt;

    @Operation(summary = "JavaScript Encrypt", description = "JavaScript Encryption")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "/v1/encrypt", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> jsEncrypt(@RequestParam(value = "key", defaultValue = "123456789") String key, @RequestBody String value) {

        return ResponseEntity.ok(javaScriptSalt.encrypt(key, value));
    }

    @Operation(summary = "JavaScript Decrypt", description = "JavaScript Decryption")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "/v1/decrypt", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> jsDecrypt(@RequestParam(value = "key", defaultValue = "123456789") String key, @RequestBody String value) {

        return ResponseEntity.ok(javaScriptSalt.decrypt(key, value).toString());
    }

    @Operation(summary = "Base64 Encode", description = "Base64 Encoding")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "/v1/base64Encode", consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> base64Encode(@RequestParam("value") String value) {

        return ResponseEntity.ok(javaScriptSalt.base64Encrypt(value));
    }

    @Operation(summary = "Base64 Decode", description = "Base64 Decoding")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "/v1/base64Decode", consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> base64Decode(@RequestParam("value") String value) {

        return ResponseEntity.ok(javaScriptSalt.base64Decrypt(value));
    }

}
