package com.teckieplanet.gradingapi.repository;

import com.teckieplanet.gradingapi.entity.Report;
import com.teckieplanet.gradingapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findReportsByStudentAndLevel(Student student, String level);
}
