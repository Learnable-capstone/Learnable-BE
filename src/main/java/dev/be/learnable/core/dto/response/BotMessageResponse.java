package dev.be.learnable.core.dto.response;

import dev.be.learnable.core.domain.BotMessage;
import dev.be.learnable.core.domain.ChatRoom;
import dev.be.learnable.core.dto.ChatRoomDto;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BotMessageResponse {
    private final Long id;
    private final Long chatroomId;
    private final String content;
    private final Boolean isBookmarked;
    private final LocalDateTime createdAt;

    public static BotMessageResponse of(Long id, Long chatroomId, String content, Boolean isBookmarked, LocalDateTime createdAt) {
        return new BotMessageResponse(id, chatroomId, content, isBookmarked, createdAt);
    }

    public static BotMessageResponse from(BotMessage botMessage) {
        return BotMessageResponse.of(
            botMessage.getId(),
            botMessage.getChatRoom().getId(),
            botMessage.getContent(),
            botMessage.getIsBookmarked(),
            botMessage.getCreatedAt()
        );
    }
}
