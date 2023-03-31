package dev.be.learnable.core.dto;

import dev.be.learnable.core.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberDto {
    private final String username;
    private final String email;
    private final String role;
    private final String socialtype;
    private final String socialid;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static MemberDto from(Member member) {
        return new MemberDto(
            member.getUsername(),
            member.getEmail(),
            member.getRole(),
            member.getSocialtype(),
            member.getSocialid(),
            member.getCreatedAt(),
            member.getUpdatedAt()
        );
    }
}
