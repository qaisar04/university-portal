package kz.baltabayev.studentservice.comsumer;

import kz.baltabayev.studentservice.model.dto.StudentRequest;
import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.model.entity.StudentInfo;
import kz.baltabayev.studentservice.model.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class StudentConsumer {

    private final StudentServiceImpl studentService;

    @KafkaListener(topics = "${spring.kafka.queues.student}", groupId = "student-service")
    public void consumeStudent(StudentRequest studentRequest) {
        studentService.save(Student.builder()
                .email(studentRequest.email())
                .studentInfo(StudentInfo.builder()
                        .firstname(studentRequest.name())
                        .build())
                .build());
    }
}
