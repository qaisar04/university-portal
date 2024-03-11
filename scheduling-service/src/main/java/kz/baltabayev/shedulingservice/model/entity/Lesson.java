package kz.baltabayev.shedulingservice.model.entity;

import jakarta.persistence.*;
import kz.baltabayev.shedulingservice.model.types.LessonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private LessonType lessonType;
    private Long teacherId;
    private Long subjectId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
