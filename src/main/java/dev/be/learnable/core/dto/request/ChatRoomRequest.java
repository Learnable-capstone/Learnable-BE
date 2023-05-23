package dev.be.learnable.core.dto.request;

import dev.be.learnable.core.dto.ChatRoomDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ChatRoomRequest {
    private final Long memberId;
    private final Long subjectId;

    private final String title;

    public static ChatRoomRequest of(Long memberId, Long subjectId, String title) {
        return new ChatRoomRequest(memberId, subjectId,title);
    }

    public ChatRoomDto toDto() {
        return ChatRoomDto.of(
            toDto().getChatroomId(),
            memberId,
            subjectId,
            title
        );
    }
}
