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
    @Column(name = "social_type")
    private String socialtype;
    @Column(name = "social_id")
    private String socialid;

    @Builder
    private Member(String username, String email, String role, String social_type, String social_id){
        this.username = username;
        this.email = email;
        this.role = role;
        this.socialtype = social_type;
        this.socialid = social_id;
    }

    public static Member of(String username, String email, String role, String social_type, String social_id) {
        return new Member(username, email,role, social_type, social_id);
    }
    public Member update(String username, String email){
        this.username = username;
        this.email = email;
        return this;
    }
}