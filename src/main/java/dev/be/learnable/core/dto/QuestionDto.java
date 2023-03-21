package dev.be.learnable.core.dto;

import dev.be.learnable.core.domain.Question;
import dev.be.learnable.core.domain.Subject;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class QuestionDto {
    private final Subject subject;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static QuestionDto from(Question question) {
        return new QuestionDto(
            question.getSubject(),
            question.getContent(),
            question.getCreatedAt(),
            question.getUpdatedAt()
        );
    }
}
