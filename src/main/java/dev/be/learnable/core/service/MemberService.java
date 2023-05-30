package dev.be.learnable.core.service;

import dev.be.learnable.core.domain.Member;
import dev.be.learnable.core.dto.request.MemberRequest;
import dev.be.learnable.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

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
                    .build();
            result.addAttribute("userId",memberRepository.save(newMember).getId());
        }
        return result;
    }
}
