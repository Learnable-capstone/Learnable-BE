package dev.be.learnable.common.exception;

public class NotFoundBotMessageException extends BaseException {

    public NotFoundBotMessageException() {
        super(ErrorCodeAndMessages.NOT_FOUND_CHAT_ROOM_ERROR);
    }
}
