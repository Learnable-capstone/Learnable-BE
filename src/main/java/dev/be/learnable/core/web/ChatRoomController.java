package dev.be.learnable.core.web;

import static dev.be.learnable.common.response.ResponseCodeAndMessages.CREATE_CHAT_ROOM_SUCCESS;
import static dev.be.learnable.common.response.ResponseCodeAndMessages.DELETE_CHAT_ROOM_SUCCESS;
import static dev.be.learnable.common.response.ResponseCodeAndMessages.FIND_ALL_CHAT_ROOM_SUCCESS;
import static dev.be.learnable.common.response.ResponseCodeAndMessages.FIND_ONE_CHAT_ROOM_SUCCESS;
import static dev.be.learnable.common.response.ResponseCodeAndMessages.BOOKMARK_MESSAGE_SUCCESS;
import static dev.be.learnable.common.response.ResponseCodeAndMessages.UN_BOOKMARK_MESSAGE_SUCCESS;

import dev.be.learnable.common.response.BaseResponse;
import dev.be.learnable.core.dto.request.ChatRoomRequest;
import dev.be.learnable.core.dto.response.ChatRoomResponse;
import dev.be.learnable.core.dto.response.ChatRoomsResponse;
import dev.be.learnable.core.dto.response.ChatRoomDetailResponse;
import dev.be.learnable.core.service.ChatRoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        chatRoomService.create(chatRoomRequest.toDto());
        return new BaseResponse<>(CREATE_CHAT_ROOM_SUCCESS, null);
    }

    @GetMapping
    public BaseResponse<ChatRoomsResponse> findAll(Long memberId) {
        List<ChatRoomResponse> chatRoomResponses = chatRoomService.findAll(memberId);
        ChatRoomsResponse response = ChatRoomsResponse.of(chatRoomResponses);
        return new BaseResponse<>(FIND_ALL_CHAT_ROOM_SUCCESS, response);
    }

    @GetMapping("/{chatroomId}")
    public BaseResponse<ChatRoomDetailResponse> findOne(@PathVariable Long chatroomId) {
        ChatRoomDetailResponse response = chatRoomService.findOne(chatroomId);
        return new BaseResponse<>(FIND_ONE_CHAT_ROOM_SUCCESS,response);
    }

    @PatchMapping("/bookmarks/{botMessageId}")
    public BaseResponse<Void> isBookmarked(
        @PathVariable Long botMessageId
    ) {
        chatRoomService.isBookmarked(botMessageId);
        return new BaseResponse<>(BOOKMARK_MESSAGE_SUCCESS, null);
    }

    @PatchMapping("/unbookmarks/{botMessageId}")
    public BaseResponse<Void> isUnBookmarked(
        @PathVariable Long botMessageId
    ) {
        chatRoomService.isUnBookmarked(botMessageId);
        return new BaseResponse<>(UN_BOOKMARK_MESSAGE_SUCCESS, null);
    }

    @DeleteMapping("/{chatroomId}")
    public BaseResponse<Void> delete(@PathVariable Long chatroomId) {
        chatRoomService.delete(chatroomId);
        return new BaseResponse<>(DELETE_CHAT_ROOM_SUCCESS, null);
    }
}
