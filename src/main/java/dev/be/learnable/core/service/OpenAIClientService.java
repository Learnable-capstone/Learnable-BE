package dev.be.learnable.core.service;


import dev.be.learnable.common.config.OpenAIClientConfig;
import dev.be.learnable.core.domain.BotMessage;
import dev.be.learnable.core.domain.ChatRoom;
import dev.be.learnable.core.domain.UserMessage;
import dev.be.learnable.core.dto.Message;
import dev.be.learnable.core.dto.request.ChatGPTRequest;
import dev.be.learnable.core.dto.request.ChatRequest;
import dev.be.learnable.core.dto.response.ChatGPTResponse;
import dev.be.learnable.core.repository.BotMessageRepository;
import dev.be.learnable.core.repository.ChatRoomRepository;
import dev.be.learnable.core.repository.UserMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAIClientService {

    private final OpenAIClient openAIClient;
    private final OpenAIClientConfig openAIClientConfig;
    private final ChatRoomRepository chatRoomRepository;
    private final BotMessageRepository botMessageRepository;
    private final UserMessageRepository userMessageRepository;

    private final static String ROLE_USER = "user";
    private final static String MESSAGE_TYPE_FIRST = "라는 질문에";
    private final static String MESSAGE_TYPE_SECOND = "라고 답변했어. 10점 만점에 몇점 짜리 답변이고 그렇게 평가한 이유가 뭐야?";

    // GPT 질문에 대한 유저 응답
    @Transactional
    public ChatGPTResponse chat(ChatRequest chatRequest, Long chatroomId) {
        ChatRoom chatRoom = chatRoomRepository.getReferenceById(chatroomId);
        UserMessage userMessage = UserMessage.of(chatRoom,chatRequest.getQuestion(),false);
        userMessageRepository.save(userMessage);
        List<BotMessage> botMessage = botMessageRepository.findBotMessageByChatRoom_Id(chatroomId);
        String question = botMessage.get(botMessage.size()-1).getContent();
        Message message = Message.builder()
                .role(ROLE_USER)
                .content(question + MESSAGE_TYPE_FIRST + chatRequest.getQuestion() + MESSAGE_TYPE_SECOND)
                .build();
        ChatGPTRequest chatGPTRequest = ChatGPTRequest.builder()
                .model(openAIClientConfig.getModel())
                .messages(Collections.singletonList(message))
                .build();
        return openAIClient.chat(chatGPTRequest,chatroomId);
    }
}