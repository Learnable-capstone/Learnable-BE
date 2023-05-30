package dev.be.learnable.core.web;

import dev.be.learnable.core.dto.request.MemberRequest;
import dev.be.learnable.core.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public Map<String, Object> socialLogin(@RequestBody MemberRequest memberRequest){
        return memberService.login(memberRequest);
    }
}
