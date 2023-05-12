package dev.be.learnable.core.service;

import dev.be.learnable.common.config.OpenAIClientConfig;
import dev.be.learnable.core.dto.request.ChatGPTRequest;
import dev.be.learnable.core.dto.response.ChatGPTResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "openai-service",
        url = "${openai-service.urls.base-url}",
        configuration = OpenAIClientConfig.class
)
public interface OpenAIClient {

    @PostMapping(value = "${openai-service.urls.chat-url}", headers = {"Content-Type=application/json"})
    ChatGPTResponse chat(@RequestBody ChatGPTRequest chatGPTRequest, @RequestParam Long chatroomId);
}