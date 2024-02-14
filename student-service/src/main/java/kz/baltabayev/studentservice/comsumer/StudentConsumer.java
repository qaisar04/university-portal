package kz.baltabayev.studentservice.comsumer;

import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.model.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StudentConsumer {

    private final StudentServiceImpl studentService;

    @KafkaListener(topics = "${spring.kafka.queues.student}", groupId = "student-service")
    public void consumeStudent(Student student) {
        studentService.save(student);
    }
}
