package kz.baltabayev.subjectservice.model.payload;


import java.util.Set;

public class Teacher {
    private Long id;
    private String name;
    private Set<Long> subjectIds;
}
