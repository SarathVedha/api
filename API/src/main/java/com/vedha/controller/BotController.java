package com.vedha.controller;

import com.vedha.service.BotService;
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
@RequestMapping("/bot")
@RequiredArgsConstructor
@Tag(name = "Bot", description = "Chat Bot")
public class BotController {

    private final BotService botService;

    @Operation(summary = "Chat With Me", description = "Chat With Me")
    @ApiResponse(responseCode = "200",description = "HTTP Status 200 OK")
    @PostMapping(value = "/v1/chat", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> chatBot(@RequestBody String promptMessage) {

        return ResponseEntity.ok(botService.connectBot(promptMessage));

    }
}
