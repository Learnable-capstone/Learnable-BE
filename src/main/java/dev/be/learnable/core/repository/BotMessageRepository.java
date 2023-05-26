package dev.be.learnable.core.repository;

import dev.be.learnable.core.domain.BotMessage;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BotMessageRepository extends JpaRepository<BotMessage, Long> {
    List<BotMessage> findBotMessageByChatRoom_Id(Long chatroomId);

    @Query("SELECT b.answer FROM BotMessage b WHERE b.chatRoom.id = :chatRoomId ORDER BY b.id DESC")
    List<String> findLatestAnswerByChatRoomId(@Param("chatRoomId") Long chatRoomId, Pageable pageable);

}
