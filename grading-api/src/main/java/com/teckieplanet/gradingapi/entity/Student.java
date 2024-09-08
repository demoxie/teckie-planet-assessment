package com.teckieplanet.gradingapi.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseEntity{
    private String name;
    private String username;
    private String level;
}
