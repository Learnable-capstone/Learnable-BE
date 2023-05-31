package dev.be.learnable.core.web;

import static dev.be.learnable.common.response.ResponseCodeAndMessages.UPDATE_MEMBER_INFO_SUCCESS;

import dev.be.learnable.common.response.BaseResponse;
import dev.be.learnable.core.dto.request.MemberInfoRequest;
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

    @PatchMapping("/userInfo/{userId}")
    public BaseResponse<Void> updateUserInfo(
        @PathVariable Long userId,
        @RequestBody MemberInfoRequest memberRequest) {
        memberService.updateUserInfo(userId, memberRequest);
        return new BaseResponse<>(UPDATE_MEMBER_INFO_SUCCESS, null);
    }
}
