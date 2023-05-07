package dev.be.learnable.core.web;


import dev.be.learnable.core.dto.ChatGPTResponse;
import dev.be.learnable.core.dto.ChatRequest;
import dev.be.learnable.core.dto.Choice;
import dev.be.learnable.core.dto.Message;
import dev.be.learnable.core.dto.Usage;
import dev.be.learnable.core.service.OpenAIClientService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class ChatController {

    private final OpenAIClientService openAIClientService;

    @PostMapping(value = "/chat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChatGPTResponse chat(@RequestBody ChatRequest chatRequest){
        return openAIClientService.chat(chatRequest);
    }

    @PostMapping(value = "/chat/test")
    public ChatGPTResponse testChat(@RequestBody ChatRequest chatRequest) {
        Message message = new Message("assistant", "Hello! How can I assist you today?");
        List<Choice> choices = new ArrayList<>();
        Choice choice = new Choice();
        choice.setMessage(message);
        choice.setFinish_reason("stop");
        choice.setIndex(0);

        choices.add(choice);

        Usage usage = new Usage();
        usage.setCompletion_tokens(9);
        usage.setTotal_tokens(18);
        usage.setPrompt_tokens(9);

        return new ChatGPTResponse(
            "chatcmpl-7DYXjSZXUpjeUqydzZsm9a055uPho",
            "chat.completion",
            "gpt-3.5-turbo-0301",
            LocalDate.now(),
            choices,
            usage
        );
    }
}