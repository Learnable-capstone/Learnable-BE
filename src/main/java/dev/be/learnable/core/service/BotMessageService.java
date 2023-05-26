package dev.be.learnable.core.service;

import dev.be.learnable.core.domain.BotMessage;
import dev.be.learnable.core.domain.ChatRoom;
import dev.be.learnable.core.domain.Question;
import dev.be.learnable.core.repository.BotMessageRepository;
import dev.be.learnable.core.repository.ChatRoomRepository;
import dev.be.learnable.core.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotMessageService {

    private final QuestionRepository questionRepository;
    private final BotMessageRepository botMessageRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public String getRandomQuestionBySubject(Long chatRoomId, Long subjectId){
        List<Question> questions = questionRepository.findBySubjectId(subjectId);
        ChatRoom chatRoom = chatRoomRepository.getReferenceById(chatRoomId);
        Random random = new Random();
        int randomIndex = random.nextInt(questions.size());
        String content = questions.get(randomIndex).getContent();
        String answer = questions.get(randomIndex).getAnswer();
        BotMessage botMessage = BotMessage.of(chatRoom,content, answer,false);
        botMessageRepository.save(botMessage);
        return content;
    }

    public String getAnswerByQuestion(Long chatRoomId){
        Pageable pageable = PageRequest.of(0,1);
        List<String> latestAnswer = botMessageRepository.findLatestAnswerByChatRoomId(chatRoomId,pageable);
        return latestAnswer.get(0);
    }
}