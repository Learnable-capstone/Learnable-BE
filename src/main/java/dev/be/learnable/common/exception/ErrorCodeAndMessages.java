package dev.be.learnable.common.exception;

import dev.be.learnable.common.CodeAndMessages;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorCodeAndMessages implements CodeAndMessages {
    /**
     * 400 Bad Request
     */
    BAD_REQUEST_ERROR("E-BR001", "잘못된 요청입니다."),
    CHAT_ROOM_NOT_MATCH_MEMBER_ERROR("E-BR002", "채팅방 생성자가 해당 멤버가 아닙니다."),

    /**
     * 404 Not Found
     */
    NOT_FOUND_ERROR("E-NF001", "존재하지 않습니다."),
    NOT_FOUND_CHAT_ROOM_ERROR("E-NF002", "해당 ID를 가진 채팅방이 존재하지 않습니다."),
    ;


    private final String code;
    private final String message;

    ErrorCodeAndMessages(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
