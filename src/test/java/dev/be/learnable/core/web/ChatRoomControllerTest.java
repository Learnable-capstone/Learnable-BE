package dev.be.learnable.core.web;

import static dev.be.learnable.common.response.ResponseCodeAndMessages.CREATE_CHAT_ROOM_SUCCESS;
import static dev.be.learnable.common.response.ResponseCodeAndMessages.DELETE_CHAT_ROOM_SUCCESS;
import static dev.be.learnable.common.response.ResponseCodeAndMessages.FIND_ALL_CHAT_ROOM_SUCCESS;
import static dev.be.learnable.common.response.ResponseCodeAndMessages.FIND_ONE_CHAT_ROOM_SUCCESS;
import static dev.be.learnable.common.response.ResponseCodeAndMessages.BOOKMARK_MESSAGE_SUCCESS;
import static dev.be.learnable.common.response.ResponseCodeAndMessages.UN_BOOKMARK_MESSAGE_SUCCESS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.be.learnable.common.response.BaseResponse;
import dev.be.learnable.core.dto.ChatRoomDto;
import dev.be.learnable.core.dto.request.ChatRoomRequest;
import dev.be.learnable.core.dto.response.ChatRoomDetailResponse;
import dev.be.learnable.core.dto.response.ChatRoomsResponse;
import dev.be.learnable.core.service.ChatRoomService;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@DisplayName("[컨트롤러] 채팅방 테스트")
@WebMvcTest(ChatRoomController.class)
class ChatRoomControllerTest {
    private MockMvc mvc;
    @MockBean private ChatRoomService chatRoomService;
    @Autowired private ObjectMapper objectMapper;

    ChatRoomControllerTest(
        @Autowired MockMvc mvc
    ) {
        this.mvc = mvc;
    }

    @Test
    @DisplayName("[POST] 채팅방 생성 성공 테스트")
    void createChatRoom_success() throws Exception {
        //given
        ChatRoomRequest chatRoomRequest = ChatRoomRequest.of(1L, 1L, "test-subject", "test-title");
        BaseResponse<Void> baseResponse = new BaseResponse<>(CREATE_CHAT_ROOM_SUCCESS, null);
        willDoNothing().given(chatRoomService).create(any(ChatRoomDto.class));

        //when & then
        mvc.perform(
            post("/chatrooms")
                .content(objectMapper.writeValueAsString(chatRoomRequest))
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));

        then(chatRoomService).should().create(any(ChatRoomDto.class));
    }

    @Test
    @DisplayName("[GET] 채팅방 전체 리스트 조회 성공 테스트")
    void findAllChatRoom_success() throws Exception {
        //given
        ChatRoomsResponse response = ChatRoomsResponse.of(Collections.emptyList());
        BaseResponse<ChatRoomsResponse> baseResponse = new BaseResponse<>(FIND_ALL_CHAT_ROOM_SUCCESS, response);
        given(chatRoomService.findAll(anyLong())).willReturn(Collections.emptyList());

        //when & then
        mvc.perform(get("/chatrooms?memberId=1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));

        then(chatRoomService).should().findAll(anyLong());
    }

    @Test
    @DisplayName("[GET] 채팅방 단일 조회 성공 테스트")
    void findOneChatRoom_success() throws Exception {
        //given
        Long chatroomId = 1L;
        ChatRoomDetailResponse response = ChatRoomDetailResponse.of(chatroomId, "title", "network",Collections.emptyList(), Collections.emptyList());
        BaseResponse<ChatRoomDetailResponse> baseResponse = new BaseResponse<>(FIND_ONE_CHAT_ROOM_SUCCESS, response);
        given(chatRoomService.findOne(chatroomId)).willReturn(response);

        //when & then
        mvc.perform(get("/chatrooms/1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));
        then(chatRoomService).should().findOne(anyLong());
    }

    @Test
    @DisplayName("[PATCH] 채팅방 메시지 북마크 성공 테스트")
    void bookmarkChatRoom_success() throws Exception {
        //given
        Long botMessageId = 1L;
        BaseResponse<Void> baseResponse = new BaseResponse<>(BOOKMARK_MESSAGE_SUCCESS, null);
        willDoNothing().given(chatRoomService).isBookmarked(botMessageId);

        //when & then
        mvc.perform(
                patch("/chatrooms/bookmarks/" + botMessageId)
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));

        then(chatRoomService).should().isBookmarked(botMessageId);
    }

    @Test
    @DisplayName("[PATCH] 채팅방 메시지 북마크 취소 성공 테스트")
    void unBookmarkChatRoom_success() throws Exception {
        //given
        Long botMessageId = 1L;
        BaseResponse<Void> baseResponse = new BaseResponse<>(UN_BOOKMARK_MESSAGE_SUCCESS, null);
        willDoNothing().given(chatRoomService).isUnBookmarked(botMessageId);

        //when & then
        mvc.perform(
                patch("/chatrooms/unbookmarks/" + botMessageId)
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));

        then(chatRoomService).should().isUnBookmarked(botMessageId);
    }

    @Test
    @DisplayName("[DELETE] 채팅방 삭제 성공 테스트")
    void deleteChatRoom_success() throws Exception {
        //given
        Long chatroomId = 1L;
        BaseResponse<Void> baseResponse = new BaseResponse<>(DELETE_CHAT_ROOM_SUCCESS, null);
        willDoNothing().given(chatRoomService).delete(chatroomId);

        //when & then
        mvc.perform(
                delete("/chatrooms/" + chatroomId)
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));

        then(chatRoomService).should().delete(chatroomId);
    }


    /**
     * 한글 깨짐 현상 해결 작업
     */
    @BeforeEach
    void init(WebApplicationContext wc) {
        mvc = MockMvcBuilders
            .webAppContextSetup(wc)
            .addFilter(new CharacterEncodingFilter("UTF-8", true))
            .build();
    }
}