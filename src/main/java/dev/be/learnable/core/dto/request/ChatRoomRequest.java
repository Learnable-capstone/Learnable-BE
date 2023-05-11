package dev.be.learnable.core.dto.request;

import dev.be.learnable.core.dto.ChatRoomDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ChatRoomRequest {
    private final Long id;
    private final Long memberId;
    private final Long subjectId;

    private final String subjectName;
    private final String title;

    public static ChatRoomRequest of(Long id, Long memberId, Long subjectId, String subjectName, String title) {
        return new ChatRoomRequest(id, memberId, subjectId, subjectName,title);
    }

    public ChatRoomDto toDto() {
        return ChatRoomDto.of(
            id,
            memberId,
            subjectId,
            subjectName,
            title
        );
    }
}
