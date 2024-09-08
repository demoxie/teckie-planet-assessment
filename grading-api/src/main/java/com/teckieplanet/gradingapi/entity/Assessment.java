package com.teckieplanet.gradingapi.entity;

import jakarta.persistence.Entity;
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
public class Assessment extends BaseEntity{
    private String name;
    private Double score;
    private String subject;
    private String date;
    private String level;
}
