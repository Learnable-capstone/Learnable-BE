package dev.be.learnable.core.repository;

import dev.be.learnable.core.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
