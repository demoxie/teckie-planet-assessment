package com.teckieplanet.gradingapi.repository;

import com.teckieplanet.gradingapi.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findSubjectByNameAndLevel(String name, String level);

    Optional<Subject> findByNameAndLevel(String subject, String level);

    Optional<Subject> findByName(String subject);
}
