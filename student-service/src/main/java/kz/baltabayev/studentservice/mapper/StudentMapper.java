package kz.baltabayev.studentservice.mapper;

import kz.baltabayev.studentservice.model.dto.StudentRequest;
import kz.baltabayev.studentservice.model.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toStudent(StudentRequest studentRequest);

    StudentRequest toDto(Student student);
}
