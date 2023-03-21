package dev.be.learnable.core.dto;

import dev.be.learnable.core.domain.Subject;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SubjectDto {
    private final String subjectName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static SubjectDto from(Subject subject) {
        return new SubjectDto(
            subject.getSubjectName(),
            subject.getCreatedAt(),
            subject.getUpdatedAt()
        );
    }
}
