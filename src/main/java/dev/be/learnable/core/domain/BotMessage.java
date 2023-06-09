package dev.be.learnable.core.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
public class BotMessage extends BaseEntity {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private ChatRoom chatRoom;

    private String content;

    @Column(length = 1000)
    private String answer;

    private Boolean isBookmarked;

    private BotMessage(ChatRoom chatRoom, String content, String answer,Boolean isBookmarked) {
        this.chatRoom = chatRoom;
        this.content = content;
        this.answer = answer;
        this.isBookmarked = isBookmarked;
    }

    public static BotMessage of(ChatRoom chatRoom, String content, String answer,Boolean isBookmarked) {
        return new BotMessage(chatRoom, content, answer ,isBookmarked);
    }

    public void updateBookmarked(Boolean bookmarked) {
        isBookmarked = bookmarked;
    }
}
