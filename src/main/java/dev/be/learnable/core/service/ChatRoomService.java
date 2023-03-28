package dev.be.learnable.core.service;

import dev.be.learnable.core.domain.Member;
import dev.be.learnable.core.domain.Subject;
import dev.be.learnable.core.dto.ChatRoomDto;
import dev.be.learnable.core.repository.ChatRoomRepository;
import dev.be.learnable.core.repository.MemberRepository;
import dev.be.learnable.core.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final SubjectRepository subjectRepository;

    @Transactional
    public void create(ChatRoomDto chatRoomDto) {
        log.info("[채팅방 생성] chatRoomDto = {}", chatRoomDto);
        Member member = memberRepository.getReferenceById(chatRoomDto.getMemberId());
        Subject subject = subjectRepository.getReferenceById(chatRoomDto.getSubjectId());
        chatRoomRepository.save(chatRoomDto.toEntity(member, subject));
    }
}
