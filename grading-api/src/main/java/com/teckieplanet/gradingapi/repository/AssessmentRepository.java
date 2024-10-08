package com.teckieplanet.gradingapi.repository;

import com.teckieplanet.gradingapi.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
}
