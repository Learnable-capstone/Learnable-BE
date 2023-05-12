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
    private final Long memberId;
    private final Long subjectId;
    private final String subjectName;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static ChatRoomDto of(Long memberId, Long subjectId, String subjectName, String title, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new ChatRoomDto(memberId, subjectId, subjectName, title,createdAt, updatedAt);
    }

    public static ChatRoomDto of(Long memberId, Long subjectId, String subjectName, String title) {
        return ChatRoomDto.of(memberId, subjectId, subjectName,title, null, null);
    }
    public static ChatRoomDto from(ChatRoom chatRoom) {
        return ChatRoomDto.of(
            chatRoom.getMember().getId(),
            chatRoom.getSubject().getId(),
            chatRoom.getSubject().getSubjectName(),
            chatRoom.getTitle(),
            chatRoom.getCreatedAt(),
            chatRoom.getUpdatedAt()
        );
    }

    public ChatRoom toEntity(Member member, Subject subject) {
        return ChatRoom.of(
            member,
            subject,
            title
        );
    }
}
