package dev.be.learnable.core.service;

import dev.be.learnable.core.domain.Member;
import dev.be.learnable.core.dto.request.MemberInfoRequest;
import dev.be.learnable.core.dto.request.MemberRequest;
import dev.be.learnable.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.webjars.NotFoundException;

import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Map<String, Object> login(MemberRequest memberRequest){
        ModelMap result = new ModelMap();
        Member member = memberRepository.findBySocialIdAndSocialType(memberRequest.getSocialId(), memberRequest.getSocialType());
        if(member != null){
            result.addAttribute("userId", member.getId());
        }
        else{
            Member newMember = Member.builder()
                    .username(memberRequest.getUsername())
                    .email(memberRequest.getEmail())
                    .role("ROLE_USER")
                    .socialType(memberRequest.getSocialType())
                    .socialId(memberRequest.getSocialId())
                    .profileIdx(memberRequest.getProfileIdx())
                    .build();
            result.addAttribute("userId",memberRepository.save(newMember).getId());
        }
        return result;
    }

    public Map<String, Object> getUserInfo(Long userId){
        Member member = memberRepository.findById(userId).orElseThrow(()-> new NotFoundException("해당 유저를 찾을 수 없습니다."));
        ModelMap result = new ModelMap();
        result.addAttribute("username", member.getUsername());
        result.addAttribute("profileIdx", member.getProfileIdx());
        result.addAttribute("createdAt", member.getCreatedAt());
        return result;
    }

    public void updateUserInfo(Long userId, MemberInfoRequest memberRequest) {
        Member member = memberRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));
        member.setUsername(memberRequest.getUsername());
        member.setProfileIdx(memberRequest.getProfileIdx());
    }
}
