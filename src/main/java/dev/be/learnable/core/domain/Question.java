package dev.be.learnable.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
public class Question extends BaseEntity {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Subject subject;

    private String content;

    @Column(length = 1000)
    private String answer;

    private Question(Subject subject, String content, String answer) {
        this.subject = subject;
        this.content = content;
        this.answer = answer;
    }

    public static Question of(Subject subject, String content, String answer) {
        return new Question(subject, content,answer);
    }
}
