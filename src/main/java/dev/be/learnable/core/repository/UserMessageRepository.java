package dev.be.learnable.core.repository;

import dev.be.learnable.core.domain.UserMessage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
    List<UserMessage> findUserMessageByChatRoom_Id(Long chatroomId);

    void deleteAllByChatRoom_Id(Long chatroomId);
}
