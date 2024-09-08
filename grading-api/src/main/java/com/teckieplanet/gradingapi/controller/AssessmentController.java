package com.teckieplanet.gradingapi.controller;

import com.teckieplanet.gradingapi.dto.AssessmentDTO;
import com.teckieplanet.gradingapi.entity.Report;
import com.teckieplanet.gradingapi.model.APIResponse;
import com.teckieplanet.gradingapi.services.AssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("assessments")
@RequiredArgsConstructor
public class AssessmentController {
    private final AssessmentService assessmentService;

    @PostMapping("/save")
    public ResponseEntity<APIResponse<List<Report>>> saveScores(@RequestBody List<AssessmentDTO> assessmentDTOS, @RequestHeader String transactionId) {
        APIResponse<List<Report>> apiResponse = assessmentService.saveScores(assessmentDTOS, transactionId);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping("/report/{studentId}/{level}")
    public ResponseEntity<APIResponse<List<Report>>> getReport(@PathVariable long studentId, @PathVariable String level, @RequestHeader String transactionId) {
        APIResponse<List<Report>> apiResponse = assessmentService.getReport(studentId, level, transactionId);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
