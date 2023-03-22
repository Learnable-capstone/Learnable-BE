package dev.be.learnable.core.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class UserMessage extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private ChatRoom chatRoom;

    private String content;

    private Boolean isBookmarked;

    private UserMessage(ChatRoom chatRoom, String content, Boolean isBookmarked) {
        this.chatRoom = chatRoom;
        this.content = content;
        this.isBookmarked = isBookmarked;
    }

    public static UserMessage of(ChatRoom chatRoom, String content, Boolean isBookmarked) {
        return new UserMessage(chatRoom, content, isBookmarked);
    }
}
