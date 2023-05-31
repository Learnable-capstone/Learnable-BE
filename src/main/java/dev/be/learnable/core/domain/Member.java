package dev.be.learnable.core.domain;


import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String role; // ROLE_USER , ROLE_ADMIN
    private String socialType;
    private String socialId;
    private Long profileIdx;

    @Builder
    private Member(String username, String email, String role, String socialType, String socialId, Long profileIdx){
        this.username = username;
        this.email = email;
        this.role = role;
        this.socialType = socialType;
        this.socialId = socialId;
        this.profileIdx = profileIdx;
    }

    public static Member of(String username, String email, String role, String socialType, String socialId, Long profileIdx) {
        return new Member(username, email,role, socialType, socialId, profileIdx);
    }
    public Member update(String username, String email){
        this.username = username;
        this.email = email;
        return this;
    }
}