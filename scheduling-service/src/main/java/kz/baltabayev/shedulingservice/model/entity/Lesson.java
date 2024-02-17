package kz.baltabayev.shedulingservice.model.entity;

import java.time.LocalDateTime;

public class Lesson {
    private Long id;
    private Long teacherId;
    private Long subjectId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
