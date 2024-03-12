package kz.baltabayev.studentservice.consumer;

import kz.baltabayev.studentservice.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
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
