package com.vedha.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BotRequestDTO {

    @Value("${openai.model}")
    private String model;

    private List<MessageDTO> messages;

    @Value("${openai.max-completions}")
    private int n;

    @Value("${openai.temperature}")
    private double temperature;

    @Value("${openai.max_tokens}")
    private int max_tokens;
}
