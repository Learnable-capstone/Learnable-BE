package dev.be.learnable.core.service;


import dev.be.learnable.common.config.OpenAIClientConfig;
import dev.be.learnable.common.exception.NotFoundChatRoomException;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        ChatRoom chatRoom = chatRoomRepository.findById(chatroomId)
            .orElseThrow(NotFoundChatRoomException::new);

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

        ChatGPTResponse response = openAIClient.chat(chatGPTRequest, chatroomId);

        Pattern pattern = Pattern.compile("(\\d+)점");
        Matcher matcher = pattern.matcher(response.getContent());
        long minScore = 10L;
        boolean flag = false;
        while(matcher.find()) {
            flag = true;
            minScore = Math.min(minScore, Long.parseLong(matcher.group(1)));
        }
        if (flag) chatRoom.updateChatroomInfo(chatRoom.getAnswerCnt(),minScore);
        else chatRoom.updateChatroomInfo(chatRoom.getAnswerCnt(), 0L);
        return response;
    }


}