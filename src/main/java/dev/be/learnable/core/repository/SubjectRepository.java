package dev.be.learnable.core.repository;

import dev.be.learnable.core.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
