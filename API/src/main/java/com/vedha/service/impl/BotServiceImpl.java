package com.vedha.service.impl;

import com.vedha.service.BotService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class BotServiceImpl implements BotService {

//    private final RestClient restClient;

    @Override
    public String connectBot(String prompt) {

        Optional.ofNullable(prompt).orElseThrow(() -> new RuntimeException("message should not be empty"));

//        List<MessageDTO> messageDTOS = List.of(MessageDTO.builder().role("user").content(prompt).build());

//        BotRequestDTO build = BotRequestDTO.builder().messages(messageDTOS).build();

//        log.error(build.toString());

//        BotResponseDTO botResponseDTO = Optional.ofNullable(restClient.post().body(build).retrieve().toEntity(BotResponseDTO.class).getBody())
//                .orElseThrow(() -> new RuntimeException("Bot Service Went Down, Please try later"));

//        return botResponseDTO.getChoices().get(0).getMessage().getContent();

        return "Bot Service Went Down, Please try after sometime";
    }
}
