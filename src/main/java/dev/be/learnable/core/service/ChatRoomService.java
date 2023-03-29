package dev.be.learnable.core.service;

import static java.util.stream.Collectors.toList;

import dev.be.learnable.core.domain.Member;
import dev.be.learnable.core.domain.Subject;
import dev.be.learnable.core.dto.ChatRoomDto;
import dev.be.learnable.core.dto.response.ChatRoomResponse;
import dev.be.learnable.core.repository.ChatRoomRepository;
import dev.be.learnable.core.repository.MemberRepository;
import dev.be.learnable.core.repository.SubjectRepository;
import java.util.List;
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

    public List<ChatRoomResponse> findAll(Long memberId) {
        log.info("[채팅방 전체 리스트 조회] memberId = {}", memberId);
        return chatRoomRepository.findChatRoomsByMember_Id(memberId)
            .stream()
            .map(ChatRoomDto::from)
            .map(ChatRoomResponse::from)
            .collect(toList());
    }
}
