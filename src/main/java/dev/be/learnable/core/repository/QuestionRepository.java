package dev.be.learnable.core.repository;

import dev.be.learnable.core.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySubjectId(Long subjectId);
}
