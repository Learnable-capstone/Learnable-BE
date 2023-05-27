package dev.be.learnable.core.dto.response;

import dev.be.learnable.core.dto.ChatRoomDto;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChatRoomResponse {
    private final Long chatroomId;
    private final Long memberId;
    private final Long subjectId;
    private final String title;
    private final Long answerCnt; // 유저 대답 수
    private final Long totalScore; // 채팅방에서 얻은 점수
    private final LocalDateTime createdAt;

    public static ChatRoomResponse of(Long chatroomId, Long memberId, Long subjectId, String title, Long answerCnt, Long totalScore, LocalDateTime createdAt) {
        return new ChatRoomResponse(chatroomId, memberId, subjectId, title, answerCnt, totalScore, createdAt);
    }

    public static ChatRoomResponse from(ChatRoomDto chatRoomDto) {
        return ChatRoomResponse.of(
            chatRoomDto.getChatroomId(),
            chatRoomDto.getMemberId(),
            chatRoomDto.getSubjectId(),
            chatRoomDto.getTitle(),
            chatRoomDto.getAnswerCnt(),
            chatRoomDto.getTotalScore(),
            chatRoomDto.getCreatedAt()
        );
    }
}
