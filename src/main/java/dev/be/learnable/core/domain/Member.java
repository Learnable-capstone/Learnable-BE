package dev.be.learnable.core.domain;


import static javax.persistence.GenerationType.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role; // ROLE_USER , ROLE_ADMIN
    private String social_type;
    private String social_id;

    @Builder
    private Member(String username, String password, String email, String role, String social_type, String social_id){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.social_type = social_type;
        this.social_id = social_id;
    }

    public static Member of(String username, String password, String email, String role, String social_type, String social_id) {
        return new Member(username, password, email, role, social_type, social_id);
    }
}