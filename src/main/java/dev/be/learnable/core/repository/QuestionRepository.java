package dev.be.learnable.core.repository;

import dev.be.learnable.core.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
