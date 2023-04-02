package dev.be.learnable.core.dto.response;

import dev.be.learnable.core.domain.BotMessage;
import dev.be.learnable.core.domain.UserMessage;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserMessageResponse {
    private final Long id;
    private final Long chatroomId;
    private final String content;
    private final Boolean isBookmarked;
    private final LocalDateTime createdAt;

    public static UserMessageResponse of(Long id, Long chatroomId, String content, Boolean isBookmarked, LocalDateTime createdAt) {
        return new UserMessageResponse(id, chatroomId, content, isBookmarked, createdAt);
    }

    public static UserMessageResponse from(UserMessage userMessage) {
        return UserMessageResponse.of(
            userMessage.getId(),
            userMessage.getChatRoom().getId(),
            userMessage.getContent(),
            userMessage.getIsBookmarked(),
            userMessage.getCreatedAt()
        );
    }
}
