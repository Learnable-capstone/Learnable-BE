package dev.be.learnable.core.repository;

import dev.be.learnable.core.domain.ChatRoom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findChatRoomsByMember_Id(Long memberId);

    void deleteAllByMember_Id(Long memberId);
}
