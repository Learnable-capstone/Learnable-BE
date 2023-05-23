package dev.be.learnable.core.dto.response;

import dev.be.learnable.core.dto.ChatRoomDto;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChatRoomResponse {
    private final Long memberId;
    private final Long subjectId;
    private final String title;
    private final LocalDateTime createdAt;

    public static ChatRoomResponse of(Long memberId, Long subjectId, String title, LocalDateTime createdAt) {
        return new ChatRoomResponse(memberId, subjectId, title, createdAt);
    }

    public static ChatRoomResponse from(ChatRoomDto chatRoomDto) {
        return ChatRoomResponse.of(
            chatRoomDto.getMemberId(),
            chatRoomDto.getSubjectId(),
            chatRoomDto.getTitle(),
            chatRoomDto.getCreatedAt()
        );
    }
}
