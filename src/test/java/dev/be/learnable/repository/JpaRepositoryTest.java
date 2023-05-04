package dev.be.learnable.repository;

import static org.assertj.core.api.Assertions.assertThat;

import dev.be.learnable.core.domain.Question;
import dev.be.learnable.core.domain.Subject;
import dev.be.learnable.core.repository.QuestionRepository;
import dev.be.learnable.core.repository.SubjectRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("local")
@DisplayName("JPA 연결 테스트")
public class JpaRepositoryTest {

    @Autowired private QuestionRepository questionRepository;
    @Autowired private SubjectRepository subjectRepository;

    @Test
    @DisplayName("select test")
    void givenTestData_whenSelecting_thenWorksFine() {
        //given

        //when
        List<Question> questions = questionRepository.findAll();

        //then
//        assertThat(questions)
//            .hasSize(6);
    }

    @Test
    @DisplayName("insert test")
    void givenTestData_whenInserting_thenWorksFine() {
        //given
        long prevCount = questionRepository.count();
        Subject subject = subjectRepository.getReferenceById(1L);
        Question question = Question.of(subject, "new Question");

        //when
        questionRepository.save(question);

        //then
        assertThat(questionRepository.count()).isEqualTo(prevCount + 1);
    }

    @Test
    @DisplayName("update test")
    void givenTestData_whenUpdating_thenWorksFine() {
        //given
        Question question = questionRepository.findById(1L).orElseThrow();
        String updateStr = UUID.randomUUID().toString();
        question.setContent(updateStr);

        //when
        Question savedQuestion = questionRepository.saveAndFlush(question);

        //then
        assertThat(savedQuestion.getContent())
            .isEqualTo(updateStr);
    }

    @Test
    @DisplayName("delete test")
    void givenTestData_whenDeleting_thenWorksFine() {
        //given
        Question question = questionRepository.findById(1L).orElseThrow();
        long prevCount = questionRepository.count();

        //when
        questionRepository.delete(question);

        //then
        assertThat(questionRepository.count())
            .isEqualTo(prevCount - 1);
    }
}
