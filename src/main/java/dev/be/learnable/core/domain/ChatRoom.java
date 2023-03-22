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

    private ChatRoom(Member member, Subject subject, String title) {
        this.member = member;
        this.subject = subject;
        this.title = title;
    }

    public static ChatRoom of(Member member, Subject subject, String title) {
        return new ChatRoom(member, subject, title);
    }
}
