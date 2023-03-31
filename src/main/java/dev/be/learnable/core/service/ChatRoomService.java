package dev.be.learnable.core.service;

import static java.util.stream.Collectors.toList;

import dev.be.learnable.common.exception.NotFoundChatRoomException;
import dev.be.learnable.core.domain.ChatRoom;
import dev.be.learnable.core.domain.Member;
import dev.be.learnable.core.domain.Subject;
import dev.be.learnable.core.dto.ChatRoomDto;
import dev.be.learnable.core.dto.response.BotMessageResponse;
import dev.be.learnable.core.dto.response.ChatRoomResponse;
import dev.be.learnable.core.dto.response.ChatRoomDetailResponse;
import dev.be.learnable.core.dto.response.UserMessageResponse;
import dev.be.learnable.core.repository.BotMessageRepository;
import dev.be.learnable.core.repository.ChatRoomRepository;
import dev.be.learnable.core.repository.MemberRepository;
import dev.be.learnable.core.repository.SubjectRepository;
import dev.be.learnable.core.repository.UserMessageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final SubjectRepository subjectRepository;
    private final BotMessageRepository botMessageRepository;
    private final UserMessageRepository userMessageRepository;

    @Transactional
    public void create(ChatRoomDto chatRoomDto) {
        log.info("[채팅방 생성] chatRoomDto = {}", chatRoomDto);
        Member member = memberRepository.getReferenceById(chatRoomDto.getMemberId());
        Subject subject = subjectRepository.getReferenceById(chatRoomDto.getSubjectId());
        chatRoomRepository.save(chatRoomDto.toEntity(member, subject));
    }

    public List<ChatRoomResponse> findAll(Long memberId) {
        log.info("[채팅방 전체 리스트 조회] memberId = {}", memberId);
        return chatRoomRepository.findChatRoomsByMember_Id(memberId)
            .stream()
            .map(ChatRoomDto::from)
            .map(ChatRoomResponse::from)
            .collect(toList());
    }

    /**
     * TODO Querydsl을 이용한 커스텀 쿼리로 최신 시간 순으로 정렬해서 보내줄 예정
     */
    public ChatRoomDetailResponse findOne(Long chatroomId) {
        log.info("[채팅방 단일 조회] chatroomId = {}", chatroomId);

        ChatRoom chatRoom = chatRoomRepository.findById(chatroomId)
            .orElseThrow(NotFoundChatRoomException::new);

        List<BotMessageResponse> botMessageResponses =
            botMessageRepository.findBotMessageByChatRoom_Id(chatroomId)
                .stream()
                .map(BotMessageResponse::from)
                .collect(toList());

        List<UserMessageResponse> userMessageResponses =
            userMessageRepository.findUserMessageByChatRoom_Id(chatroomId)
                .stream()
                .map(UserMessageResponse::from)
                .collect(toList());

        return ChatRoomDetailResponse.of(chatroomId, chatRoom.getTitle(), chatRoom.getSubject().getSubjectName(), botMessageResponses, userMessageResponses);
    }

    @Transactional
    public void delete(Long chatroomId) {
        log.info("[채팅방 삭제] chatroomId = {}", chatroomId);

        chatRoomRepository.deleteById(chatroomId);
    }
}
