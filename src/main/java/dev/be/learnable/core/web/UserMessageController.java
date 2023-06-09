package dev.be.learnable.core.web;

import dev.be.learnable.core.dto.request.ChatRequest;
import dev.be.learnable.core.dto.response.ChatGPTResponse;
import dev.be.learnable.core.service.ChatRoomService;
import dev.be.learnable.core.service.OpenAIClientService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usermessages")
public class UserMessageController {

    private final OpenAIClientService openAIClientService;
    private final ChatRoomService chatRoomService;

    // GPT 에게 유저가 질문
    @PostMapping(value = "/chat")
    public String sendQuestion(@RequestBody ChatRequest chatRequest, @RequestParam Long chatroomId){
        ChatGPTResponse response = openAIClientService.chat(chatRequest,chatroomId);
        return response.getContent();
    }

}