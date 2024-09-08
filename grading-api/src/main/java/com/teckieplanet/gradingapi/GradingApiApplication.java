package com.teckieplanet.gradingapi;

import com.teckieplanet.gradingapi.entity.Student;
import com.teckieplanet.gradingapi.repository.StudentRepository;
import com.teckieplanet.gradingapi.repository.SubjectRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.Bean;
import com.teckieplanet.gradingapi.entity.Subject;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
public class GradingApiApplication {

    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    // Constructor injection of repositories
    public GradingApiApplication(SubjectRepository subjectRepository, StudentRepository studentRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(GradingApiApplication.class, args);
    }

    // Corrected CommandLineRunner using constructor-injected repositories
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            List<Student> students = List.of(
                    Student.builder()
                            .name("John Doe")
                            .username("user1")
                            .level("Level1")
                            .build(),
                    Student.builder()
                            .name("Jane Doe")
                            .username("user2")
                            .level("Level2")
                            .build(),
                    Student.builder()
                            .name("David Doe")
                            .username("user3")
                            .level("Level3")
                            .build(),
                    Student.builder()
                            .name("Emily Doe")
                            .username("user4")
                            .level("Level3")
                            .build(),
                    Student.builder()
                            .name("Smith Joe")
                            .username("user5")
                            .level("Level3")
                            .build(),
                    Student.builder()
                            .name("Brown Jane")
                            .username("user6")
                            .level("Level3")
                            .build()
            );

            List<Subject> subjects = List.of(
                    Subject.builder()
                            .name("Math")
                            .level("Level1")
                            .build(),
                    Subject.builder()
                            .name("Science")
                            .level("Level2")
                            .build(),
                    Subject.builder()
                            .name("English")
                            .level("Level3")
                            .build(),
                    Subject.builder()
                            .name("History")
                            .level("Level3")
                            .build(),
                    Subject.builder()
                            .name("Geography")
                            .level("Level3")
                            .build()
            );

            students.forEach(student -> {
                if (studentRepository.findStudentByUsername(student.getUsername()).isEmpty()) {
                    studentRepository.save(student);
                }
            });

            subjects.forEach(subject -> {
                if (subjectRepository.findSubjectByNameAndLevel(subject.getName(), subject.getLevel()).isEmpty()) {
                    subjectRepository.save(subject);
                }
            });
        };
    }
}
