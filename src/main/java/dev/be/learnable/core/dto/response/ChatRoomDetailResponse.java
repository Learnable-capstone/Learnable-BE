package dev.be.learnable.core.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChatRoomDetailResponse {
    private final Long chatroomId;
    private final String title;
    private final String subjectName;
    private final List<BotMessageResponse> botMessages;
    private final List<UserMessageResponse> userMessages;

    public static ChatRoomDetailResponse of(Long chatroomId, String title, String subjectName, List<BotMessageResponse> botMessages, List<UserMessageResponse> userMessages) {
        return new ChatRoomDetailResponse(chatroomId, title, subjectName, botMessages, userMessages);
    }
}
