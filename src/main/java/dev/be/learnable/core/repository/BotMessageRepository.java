package dev.be.learnable.core.repository;

import dev.be.learnable.core.domain.BotMessage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BotMessageRepository extends JpaRepository<BotMessage, Long> {
    List<BotMessage> findBotMessageByChatRoom_Id(Long chatroomId);
}
