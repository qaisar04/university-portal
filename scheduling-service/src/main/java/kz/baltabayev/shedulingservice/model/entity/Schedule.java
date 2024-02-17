package kz.baltabayev.shedulingservice.model.entity;

import java.util.List;

public class Schedule {
    private Long id;
    private Long groupId;
    private Long facultyId;
    private List<Lesson> lessons;
}
