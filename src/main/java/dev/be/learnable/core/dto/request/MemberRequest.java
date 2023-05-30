package dev.be.learnable.core.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberRequest {
    private final String username;
    private final String email;
    private final String socialType;
    private final String socialId;
}
