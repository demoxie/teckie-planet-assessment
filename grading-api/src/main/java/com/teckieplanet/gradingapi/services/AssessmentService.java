package com.teckieplanet.gradingapi.services;

import com.teckieplanet.gradingapi.dto.AssessmentDTO;
import com.teckieplanet.gradingapi.entity.Report;
import com.teckieplanet.gradingapi.model.APIResponse;

import java.util.List;

public interface AssessmentService {
    APIResponse<List<Report>> getReport(long studentId, String level, String transactionId);
    APIResponse<List<Report>> saveScores(List<AssessmentDTO> assessmentDTO, String transactionId);
}
