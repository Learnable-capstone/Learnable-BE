package dev.be.learnable.core.web;

import static dev.be.learnable.common.response.ResponseCodeAndMessages.CREATE_CHAT_ROOM_SUCCESS;

import dev.be.learnable.common.response.BaseResponse;
import dev.be.learnable.core.dto.request.ChatRoomRequest;
import dev.be.learnable.core.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatrooms")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PostMapping
    public BaseResponse<Void> create(
        @RequestBody ChatRoomRequest chatRoomRequest
    ) {
        chatRoomService.createChatRoom(chatRoomRequest.toDto());
        return new BaseResponse<>(CREATE_CHAT_ROOM_SUCCESS, null);
    }
}
