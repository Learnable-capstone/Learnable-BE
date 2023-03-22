package dev.be.learnable.core.dto;

import dev.be.learnable.core.domain.Member;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberDto {
    private final String username;
    private final String password;
    private final String email;
    private final String role;
    private final String social_type;
    private final String social_id;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static MemberDto from(Member member) {
        return new MemberDto(
            member.getUsername(),
            member.getPassword(),
            member.getEmail(),
            member.getRole(),
            member.getSocial_type(),
            member.getSocial_id(),
            member.getCreatedAt(),
            member.getUpdatedAt()
        );
    }
}
