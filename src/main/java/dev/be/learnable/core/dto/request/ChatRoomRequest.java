package dev.be.learnable.core.dto.request;

import static lombok.AccessLevel.*;

import dev.be.learnable.core.dto.ChatRoomDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = PRIVATE)
public class ChatRoomRequest {
    private Long memberId;
    private Long subjectId;
    private String title;

    public ChatRoomRequest(Long memberId, Long subjectId, String title) {
        this.memberId = memberId;
        this.subjectId = subjectId;
        this.title = title;
    }

    public static ChatRoomRequest of(Long memberId, Long subjectId, String title) {
        return new ChatRoomRequest(memberId, subjectId, title);
    }

    public ChatRoomDto toDto() {
        return ChatRoomDto.of(
            memberId,
            subjectId,
            title
        );
    }
}
