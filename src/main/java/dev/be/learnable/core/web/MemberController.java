package dev.be.learnable.core.web;

import dev.be.learnable.core.dto.request.MemberRequest;
import dev.be.learnable.core.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/userInfo/{userId}")
    public Map<String, Object> getUserInfo(@PathVariable Long userId){
        return memberService.getUserInfo(userId);
    }
}
