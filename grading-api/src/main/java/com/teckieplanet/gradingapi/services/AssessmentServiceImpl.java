package com.teckieplanet.gradingapi.services;

import com.teckieplanet.gradingapi.dto.AssessmentDTO;
import com.teckieplanet.gradingapi.entity.Assessment;
import com.teckieplanet.gradingapi.entity.Report;
import com.teckieplanet.gradingapi.entity.Student;
import com.teckieplanet.gradingapi.entity.Subject;
import com.teckieplanet.gradingapi.exceptions.APIException;
import com.teckieplanet.gradingapi.model.APIResponse;
import com.teckieplanet.gradingapi.repository.AssessmentRepository;
import com.teckieplanet.gradingapi.repository.ReportRepository;
import com.teckieplanet.gradingapi.repository.StudentRepository;
import com.teckieplanet.gradingapi.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssessmentServiceImpl implements AssessmentService{
    private final AssessmentRepository assessmentRepository;
    private final StudentRepository studentRepository;
    private final ReportRepository reportRepository;
    private final SubjectRepository subjectRepository;
    @Override
    public APIResponse<List<Report>> getReport(long studentId, String level, String transactionId) {
        Optional<Student> existingStudent = studentRepository.findById(studentId);
        if(existingStudent.isEmpty()){
            throw APIException.builder()
                   .message("Student not found")
                   .transactionId(transactionId)
                   .statusCode(404)
                   .build();
        }
        List<Report> reportOptional = reportRepository.findReportsByStudentAndLevel(existingStudent.get(), level);
        if(reportOptional.isEmpty()){
            throw APIException.builder()
                   .message("Report not found")
                   .transactionId(transactionId)
                   .statusCode(404)
                   .build();
        }
        return APIResponse.<List<Report>>builder()
               .data(reportOptional)
                .transactionId(transactionId)
                .status(200)
               .build();
    }

    @Override
    public APIResponse<List<Report>> saveScores(List<AssessmentDTO> assessmentDTOS, String transactionId) {
        List<Report> reports = new ArrayList<Report>();
        assessmentDTOS.forEach(assessmentDTO -> {
            Optional<Student> existingStudent = studentRepository.findById(assessmentDTO.getStudentId());
            if(existingStudent.isEmpty()){
                throw APIException.builder()
                        .message("Student not found")
                        .transactionId(transactionId)
                        .statusCode(404)
                        .build();
            }
            List<Assessment> assessments = assessmentDTO.getAssessments();
            assessments.forEach(assessment -> {
                Optional<Subject> existingSubject = subjectRepository.findByName(assessment.getSubject());
                if(existingSubject.isEmpty()){
                    throw APIException.builder()
                           .message("Subject not found")
                           .transactionId(transactionId)
                           .statusCode(404)
                           .build();
                }
                assessmentRepository.save(assessment);
            });
            List<Double> scores = assessments.stream().map(Assessment::getScore).toList();
            Report report = Report.builder()
                    .student(existingStudent.get())
                    .totalScore(getTotalScore(scores))
                    .meanScore(getMeanScore(scores))
                    .modeScore(getMode(scores))
                    .medianScore(getMedian(scores))
                    .level(assessmentDTO.getLevel())
                    .build();
            reports.add(reportRepository.save(report));
        });

        return APIResponse.<List<Report>>builder()
                .data(reports)
                .status(200)
                .message("Successfully created")
                .transactionId(transactionId)
                .build();
    }

    public double getTotalScore(List<Double> scores) {
        return scores.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double getAverageScore(List<Double> scores) {
        return getTotalScore(scores) / scores.size();
    }

    public double getHighestScore(List<Double> scores) {
        return scores.stream().max(Double::compareTo).orElse(0.0);
    }

    public double getLowestScore(List<Double> scores) {
        return scores.stream().min(Double::compareTo).orElse(0.0);
    }

    public double getMedian(List<Double> scores) {
        if (scores == null || scores.isEmpty()) {
            throw APIException.builder()
                    .statusCode(400)
                    .message("Scores list cannot be null or empty")
                    .build();
        }

        // Create a mutable copy of the list
        List<Double> sortedScores = new ArrayList<>(scores);

        // Sort the list
        sortedScores.sort(Double::compareTo);

        int size = sortedScores.size();

        if (size % 2 == 0) {
            // Return the average of the two middle elements if the size is even
            return (sortedScores.get(size / 2 - 1) + sortedScores.get(size / 2)) / 2.0;
        } else {
            // Return the middle element if the size is odd
            return sortedScores.get(size / 2);
        }
    }


    public double getMode(List<Double> scores) {
        List<Double> sortedScores = new ArrayList<>(scores);
        sortedScores.sort(Double::compareTo);
        int maxCount = 0;
        double mode = 0;
        for (int i = 0; i < sortedScores.size() - 1; i++) {
            int count = 1;
            while (i + 1 < sortedScores.size() && sortedScores.get(i).equals(sortedScores.get(i + 1))) {
                count++;
                i++;
            }
            if (count > maxCount) {
                maxCount = count;
                mode = sortedScores.get(i);
            }
        }
        return mode;
    }

    public double getMeanScore(List<Double> scores){
        return getAverageScore(scores);
    }
}
