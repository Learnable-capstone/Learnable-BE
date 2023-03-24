package dev.be.learnable.common.config;

import feign.RequestInterceptor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAIClientConfig {
    @Value("${openai-service.api-key}")
    private String apiKey;

    @Getter
    @Setter
    @Value("${openai-service.gpt-model}")
    private String model;
    @Bean
    public RequestInterceptor apiKeyInterceptor() {
        return request -> request.header("Authorization", "Bearer " + apiKey);
    }
}