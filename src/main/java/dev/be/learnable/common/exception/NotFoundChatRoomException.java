package dev.be.learnable.common.exception;

public class NotFoundChatRoomException extends BaseException {

    public NotFoundChatRoomException() {
        super(ErrorCodeAndMessages.NOT_FOUND_CHAT_ROOM_ERROR);
    }
}
