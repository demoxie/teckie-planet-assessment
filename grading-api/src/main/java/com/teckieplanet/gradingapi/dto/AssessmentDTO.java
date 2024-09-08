package com.teckieplanet.gradingapi.dto;

import com.teckieplanet.gradingapi.entity.Assessment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentDTO {
    private Long studentId;
    private String level;
    private List<Assessment> assessments;
}
