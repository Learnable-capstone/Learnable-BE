package dev.be.learnable.core.domain;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
public class Subject extends BaseEntity{
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String subjectName;

    private Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public static Subject of(String subjectName) {
        return new Subject(subjectName);
    }
}
