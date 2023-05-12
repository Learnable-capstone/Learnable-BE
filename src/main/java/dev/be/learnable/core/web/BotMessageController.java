package dev.be.learnable.core.web;


import dev.be.learnable.core.dto.response.ChatGPTResponse;
import dev.be.learnable.core.dto.request.ChatRequest;
import dev.be.learnable.core.service.BotMessageService;
import dev.be.learnable.core.service.OpenAIClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/botmessages")
public class BotMessageController {

    private final BotMessageService botMessageService;


    // GPT가 유저에게 질문
    @GetMapping("{chatroomId}/questions/{subjectId}")
    public String getRandomQuestionBySubject(@PathVariable Long chatroomId, @PathVariable Long subjectId){
        return botMessageService.getRandomQuestionBySubject(chatroomId,subjectId);
    }

}