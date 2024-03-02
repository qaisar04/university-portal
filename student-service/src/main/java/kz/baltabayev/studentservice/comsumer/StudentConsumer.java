package kz.baltabayev.studentservice.comsumer;

import kz.baltabayev.studentservice.model.dto.StudentConsumerRequest;
import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.model.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentConsumer {

    private final StudentServiceImpl studentService;

//    @KafkaListener(topics = "${spring.kafka.queues.student}", groupId = "student-service")
//    public void consumeStudent(StudentConsumerRequest request) {
//        studentService.save(Student.builder()
//                .email(request.email())
//                .studentInfo(StudentInfo.builder()
//                        .firstname(request.name())
//                        .build())
//                .build());
//    }
}
