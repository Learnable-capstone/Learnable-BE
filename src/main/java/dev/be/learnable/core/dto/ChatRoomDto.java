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
    private final Member member;
    private final Subject subject;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static ChatRoomDto from(ChatRoom chatRoom) {
        return new ChatRoomDto(
            chatRoom.getMember(),
            chatRoom.getSubject(),
            chatRoom.getTitle(),
            chatRoom.getCreatedAt(),
            chatRoom.getUpdatedAt()
        );
    }
}
