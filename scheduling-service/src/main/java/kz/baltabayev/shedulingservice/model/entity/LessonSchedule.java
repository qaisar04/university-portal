package kz.baltabayev.shedulingservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class LessonSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Lesson lesson;
    @ManyToOne
    private Schedule schedule;
    private LocalDateTime lessonTime;
    private LocalDateTime lessonEndTime;
}
