package dev.be.learnable.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import dev.be.learnable.core.domain.ChatRoom;
import dev.be.learnable.core.domain.Member;
import dev.be.learnable.core.domain.Subject;
import dev.be.learnable.core.dto.ChatRoomDto;
import dev.be.learnable.core.dto.response.ChatRoomResponse;
import dev.be.learnable.core.repository.ChatRoomRepository;
import dev.be.learnable.core.repository.MemberRepository;
import dev.be.learnable.core.repository.SubjectRepository;
import java.time.LocalDateTime;
import java.util.List;
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
        ChatRoom chatRoom1 = createChatRoom(memberId);
        ChatRoom chatRoom2 = createChatRoom(memberId);
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

    private ChatRoom createChatRoom() {
        return createChatRoom(1L, 1L);
    }
    private ChatRoom createChatRoom(Long memberId) {
        return createChatRoom(memberId, 1L);
    }
    private ChatRoom createChatRoom(Long memberId, Long subjectId) {
        return ChatRoom.of(
            createMember(memberId),
            createSubject(subjectId),
            "title"
        );
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
            "password",
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