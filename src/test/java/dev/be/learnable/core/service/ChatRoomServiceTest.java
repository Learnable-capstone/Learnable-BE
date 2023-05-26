package dev.be.learnable.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.BOOLEAN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;

import dev.be.learnable.common.exception.NotFoundBotMessageException;
import dev.be.learnable.core.domain.BotMessage;
import dev.be.learnable.core.domain.ChatRoom;
import dev.be.learnable.core.domain.Member;
import dev.be.learnable.core.domain.Subject;
import dev.be.learnable.core.domain.UserMessage;
import dev.be.learnable.core.dto.ChatRoomDto;
import dev.be.learnable.core.dto.response.ChatRoomResponse;
import dev.be.learnable.core.dto.response.ChatRoomDetailResponse;
import dev.be.learnable.core.repository.BotMessageRepository;
import dev.be.learnable.core.repository.ChatRoomRepository;
import dev.be.learnable.core.repository.MemberRepository;
import dev.be.learnable.core.repository.SubjectRepository;
import dev.be.learnable.core.repository.UserMessageRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("[서비스] 채팅방 테스트")
@ExtendWith(MockitoExtension.class)
class ChatRoomServiceTest {

    @InjectMocks private ChatRoomService chatRoomService;
    @Mock private ChatRoomRepository chatRoomRepository;
    @Mock private MemberRepository memberRepository;
    @Mock private SubjectRepository subjectRepository;
    @Mock private BotMessageRepository botMessageRepository;
    @Mock private UserMessageRepository userMessageRepository;

    @Test
    @DisplayName("채팅방 생성 성공 테스트")
    void createChatRoom_success() throws Exception {
        //given
        ChatRoomDto dto = createChatRoomDto();
        given(chatRoomRepository.save(any(ChatRoom.class))).willReturn(createChatRoom());

        //when
        chatRoomService.create(dto);

        //then
        then(chatRoomRepository).should().save(any(ChatRoom.class));
    }

    @Test
    @DisplayName("채팅방 전체 리스트 조회 성공 테스트")
    void findAllChatRoom_success() throws Exception {
        //given
        Long memberId = 1L;
        ChatRoom chatRoom1 = createChatRoom();
        ChatRoom chatRoom2 = createChatRoom();
        given(chatRoomRepository.findChatRoomsByMember_Id(memberId))
            .willReturn(List.of(
                chatRoom1,
                chatRoom2
            ));

        //when
        List<ChatRoomResponse> chatRoomResponses = chatRoomService.findAll(memberId);

        //then
        assertThat(chatRoomResponses).hasSize(2);
        then(chatRoomRepository).should().findChatRoomsByMember_Id(memberId);
    }

    @Test
    @DisplayName("채팅방 단일 조회 성공 테스트")
    void findOneChatRoom_success() throws Exception {
        //given
        Long chatroomId = 1L;
        ChatRoom chatRoom = createChatRoom();
        BotMessage botMessage1 = createBotMessage(1L);
        BotMessage botMessage2 = createBotMessage(2L);

        UserMessage userMessage1 = createUserMessage(1L);
        UserMessage userMessage2 = createUserMessage(2L);

        given(botMessageRepository.findBotMessageByChatRoom_Id(chatroomId))
            .willReturn(List.of(
                botMessage1,
                botMessage2
            ));

        given(userMessageRepository.findUserMessageByChatRoom_Id(chatroomId))
            .willReturn(List.of(
                userMessage1,
                userMessage2
            ));


        given(chatRoomRepository.findById(chatroomId))
            .willReturn(Optional.of(chatRoom));

        //when
        ChatRoomDetailResponse response = chatRoomService.findOne(chatroomId);

        //then
        assertThat(response.getBotMessages()).hasSize(2);
        assertThat(response.getUserMessages()).hasSize(2);
        then(botMessageRepository).should().findBotMessageByChatRoom_Id(chatroomId);
        then(userMessageRepository).should().findUserMessageByChatRoom_Id(chatroomId);
    }

    @Test
    @DisplayName("채팅방 메시지 북마크 성공 테스트")
    void bookmarkChatRoom_success() {
        //given
        BotMessage botMessage = createBotMessage(1L);
        given(botMessageRepository.findById(1L)).willReturn(Optional.of(botMessage));

        //when
        chatRoomService.isBookmarked(1L);

        //then
        assertThat(botMessage.getIsBookmarked()).isEqualTo(Boolean.TRUE);
        then(botMessageRepository).should().findById(1L);
    }

    @Test
    @DisplayName("채팅방 메시지 북마크 취소 성공 테스트")
    void unBookmarkChatRoom_success() {
        //given
        BotMessage botMessage = createBotMessage(1L);
        given(botMessageRepository.findById(1L)).willReturn(Optional.of(botMessage));

        //when
        chatRoomService.isUnBookmarked(1L);

        //then
        assertThat(botMessage.getIsBookmarked()).isEqualTo(Boolean.FALSE);
        then(botMessageRepository).should().findById(1L);
    }
    @Test
    @DisplayName("채팅방 삭제 성공 테스트")
    void deleteChatRoom_success() {
        //given
        Long chatroomId = 1L;
        createChatRoom(chatroomId);
        willDoNothing().given(chatRoomRepository).deleteById(chatroomId);

        //when
        chatRoomService.delete(chatroomId);

        //then
        then(chatRoomRepository).should().deleteById(chatroomId);
    }

    private ChatRoom createChatRoom() {
        return createChatRoom(1L);
    }

    private ChatRoom createChatRoom(Long chatroomId) {

        ChatRoom chatRoom = ChatRoom.of(
            createMember(1L),
            createSubject(1L),
            "title"
        );
        ReflectionTestUtils.setField(chatRoom, "id", chatroomId);
        return chatRoom;
    }



    private BotMessage createBotMessage(Long botMessageId) {
        BotMessage botMessage = BotMessage.of(
            createChatRoom(),
            "content",
            "answer",
            Boolean.FALSE
        );
        ReflectionTestUtils.setField(botMessage, "id", botMessageId);
        return botMessage;
    }

    private UserMessage createUserMessage(Long userMessageId) {
        UserMessage userMessage = UserMessage.of(
            createChatRoom(),
            "content",
            Boolean.FALSE
        );
        ReflectionTestUtils.setField(userMessage, "id", userMessageId);
        return userMessage;
    }

    private Subject createSubject(Long subjectId) {
        Subject subject = Subject.of(
            "test-subject"
        );
        ReflectionTestUtils.setField(subject, "id", subjectId);
        return subject;
    }

    private Member createMember(Long memberId) {
        Member member = Member.of(
            "test",
            "email@gmail.com",
            "ROLE_USER",
            "social_type",
            "social_id"
        );
        ReflectionTestUtils.setField(member, "id", memberId);
        return member;
    }

    // 테스트 데이터 생성
    private ChatRoomDto createChatRoomDto() {
        return ChatRoomDto.of(
            1L,
            1L,
            1L,
            "test-title",
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

}