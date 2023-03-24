package dev.be.learnable.core.service;


import dev.be.learnable.common.config.OpenAIClientConfig;
import dev.be.learnable.core.dto.ChatGPTRequest;
import dev.be.learnable.core.dto.ChatGPTResponse;
import dev.be.learnable.core.dto.ChatRequest;
import dev.be.learnable.core.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class OpenAIClientService {

    private final OpenAIClient openAIClient;
    private final OpenAIClientConfig openAIClientConfig;

    private final static String ROLE_USER = "user";

    public ChatGPTResponse chat(ChatRequest chatRequest) {
        Message message = Message.builder()
                .role(ROLE_USER)
                .content(chatRequest.getQuestion())
                .build();
        ChatGPTRequest chatGPTRequest = ChatGPTRequest.builder()
                .model(openAIClientConfig.getModel())
                .messages(Collections.singletonList(message))
                .build();

        return openAIClient.chat(chatGPTRequest);
    }
}