package dev.be.learnable.common.response;

import dev.be.learnable.common.CodeAndMessages;
import lombok.Getter;

@Getter
public enum ResponseCodeAndMessages implements CodeAndMessages {

    /**
     * OAuth
     */
    OAUTH_LOGIN_SUCCESS("S-OA001", "로그인(혹은 회원가입)에 성공했습니다."),
    ;

    private final String code;
    private final String message;

    ResponseCodeAndMessages(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
