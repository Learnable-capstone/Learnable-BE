package dev.be.learnable.core.domain;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
public class ChatRoom extends BaseEntity{
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Member member;

    @OneToOne(fetch = LAZY)
    private Subject subject;

    private String title;

    private Long answerCnt; // 유저 대답 수

    private Long totalScore; // 채팅방에서 얻은 점수

    private Double avgScore; // 평균 점수

    public ChatRoom(Member member, Subject subject, String title, Long answerCnt, Long totalScore,
        Double avgScore) {
        this.member = member;
        this.subject = subject;
        this.title = title;
        this.answerCnt = answerCnt;
        this.totalScore = totalScore;
        this.avgScore = avgScore;
    }

    public static ChatRoom of(Member member, Subject subject, String title, Long answerCnt, Long totalScore,
        Double avgScore) {
        return new ChatRoom(member, subject, title, answerCnt, totalScore, avgScore);
    }
}
