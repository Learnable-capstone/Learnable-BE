package dev.be.learnable.core.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberInfoRequest {
    private final String username;
    private final Long profileIdx;
}
