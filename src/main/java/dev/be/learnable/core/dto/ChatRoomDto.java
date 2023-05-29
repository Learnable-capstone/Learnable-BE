package dev.be.learnable.core.dto;

import dev.be.learnable.core.domain.ChatRoom;
import dev.be.learnable.core.domain.Member;
import dev.be.learnable.core.domain.Subject;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ChatRoomDto {
    private final Long chatroomId;
    private final Long memberId;
    private final Long subjectId;
    private final String title;
    private final Long answerCnt; // 유저 대답 수
    private final Long totalScore; // 채팅방에서 얻은 점수
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    public static ChatRoomDto of(Long chatroomId,Long memberId, Long subjectId, String title,Long answerCnt, Long totalScore, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new ChatRoomDto(chatroomId, memberId, subjectId, title, answerCnt, totalScore,createdAt, updatedAt);
    }

    public static ChatRoomDto of(Long chatroomId, Long memberId, Long subjectId, String title, Long answerCnt, Long totalScore) {
        return ChatRoomDto.of(chatroomId, memberId, subjectId, title, answerCnt, totalScore, null, null);
    }

    public static ChatRoomDto of(Long memberId, Long subjectId, String title,Long answerCnt, Long totalScore) {
        return ChatRoomDto.of(null, memberId, subjectId, title, answerCnt, totalScore, null, null);
    }

    public static ChatRoomDto from(ChatRoom chatRoom) {
        return ChatRoomDto.of(
            chatRoom.getId(),
            chatRoom.getMember().getId(),
            chatRoom.getSubject().getId(),
            chatRoom.getTitle(),
            chatRoom.getAnswerCnt(),
            chatRoom.getTotalScore(),
            chatRoom.getCreatedAt(),
            chatRoom.getUpdatedAt()
        );
    }

    public ChatRoom toEntity(Member member, Subject subject) {
        return ChatRoom.of(
            member,
            subject,
            title,
            0L,
            0L
        );
    }
}
