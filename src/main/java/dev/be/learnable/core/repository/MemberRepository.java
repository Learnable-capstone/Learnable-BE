package dev.be.learnable.core.repository;

import dev.be.learnable.core.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndSocialtype(String email, String social_type);
}
