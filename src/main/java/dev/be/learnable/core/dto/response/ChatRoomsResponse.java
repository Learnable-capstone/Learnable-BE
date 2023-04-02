package dev.be.learnable.core.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChatRoomsResponse {
    private final List<ChatRoomResponse> chatRoomResponses;

    public static ChatRoomsResponse of(List<ChatRoomResponse> chatRoomResponses) {
        return new ChatRoomsResponse(chatRoomResponses);
    }
}
