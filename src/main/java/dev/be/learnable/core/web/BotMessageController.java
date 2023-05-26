package dev.be.learnable.core.web;


import dev.be.learnable.core.service.BotMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // 채팅방 별 가장 최근 질문 정답
    @GetMapping("{chatroomId}/answers")
    public String getAnswerByRandomQuestion(@PathVariable Long chatroomId){
        return botMessageService.getAnswerByQuestion(chatroomId);
    }
}