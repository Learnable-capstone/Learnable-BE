package dev.be.learnable.common.response;

import dev.be.learnable.common.CodeAndMessages;
import lombok.Getter;

@Getter
public enum ResponseCodeAndMessages implements CodeAndMessages {

    /**
     * OAuth
     */
    OAUTH_LOGIN_SUCCESS("S-OA001", "로그인(혹은 회원가입)에 성공했습니다."),

    /**
     * Member
     */
    UPDATE_MEMBER_INFO_SUCCESS("S-MI001", "유저 정보 업데이트에 성공했습니다."),

    /**
     * ChatRoom
     */
    CREATE_CHAT_ROOM_SUCCESS("S-CR001", "채팅방 생성에 성공했습니다."),
    FIND_ALL_CHAT_ROOM_SUCCESS("S-CR002", "채팅방 전체 조회에 성공했습니다."),
    FIND_ONE_CHAT_ROOM_SUCCESS("S-CR003", "채팅방 단일 조회에 성공했습니다."),
    BOOKMARK_MESSAGE_SUCCESS("S-CR004", "메시지 북마크에 성공했습니다."),
    UN_BOOKMARK_MESSAGE_SUCCESS("S-CR005", "메시지 북마크 취소에 성공했습니다."),
    DELETE_CHAT_ROOM_SUCCESS("S-CR005", "채팅방 삭제에 성공했습니다."),
    ;

    private final String code;
    private final String message;

    ResponseCodeAndMessages(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
