package com.teckieplanet.gradingapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Report extends BaseEntity{
    @ManyToOne
    private Student student;
    private String level;
    private double totalScore;
    private double meanScore;
    private double medianScore;
    private double modeScore;
}
