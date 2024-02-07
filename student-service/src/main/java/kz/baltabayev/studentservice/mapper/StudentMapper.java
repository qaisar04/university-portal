package kz.baltabayev.studentservice.mapper;

import kz.baltabayev.studentservice.model.dto.StudentRequest;
import kz.baltabayev.studentservice.model.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastname", ignore = true)
    @Mapping(target = "birthdate", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "faculty", ignore = true)
    Student toStudent(StudentRequest studentRequest);

    StudentRequest toDto(Student student);
}
