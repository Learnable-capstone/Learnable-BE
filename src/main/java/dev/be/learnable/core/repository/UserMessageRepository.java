package dev.be.learnable.core.repository;

import dev.be.learnable.core.domain.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {

}
