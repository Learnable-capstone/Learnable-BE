package dev.be.learnable.core.web;

import static dev.be.learnable.common.response.ResponseCodeAndMessages.CREATE_CHAT_ROOM_SUCCESS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.be.learnable.common.response.BaseResponse;
import dev.be.learnable.core.dto.ChatRoomDto;
import dev.be.learnable.core.dto.request.ChatRoomRequest;
import dev.be.learnable.core.service.ChatRoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
        ChatRoomRequest chatRoomRequest = ChatRoomRequest.of(1L, 1L, "test-title");
        BaseResponse<Void> baseResponse = new BaseResponse<>(CREATE_CHAT_ROOM_SUCCESS, null);
        willDoNothing().given(chatRoomService).create(any(ChatRoomDto.class));

        //when & then
        // TODO 로그인 개발되면 헤더 부분에 토큰 내용 추가해야함
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


    @BeforeEach
    void init(WebApplicationContext wc) {
        mvc = MockMvcBuilders
            .webAppContextSetup(wc)
            .addFilter(new CharacterEncodingFilter("UTF-8", true))
            .build();
    }
}