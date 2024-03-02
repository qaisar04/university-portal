package kz.baltabayev.studentservice.mapper;

import kz.baltabayev.studentservice.model.dto.StudentRequest;
import kz.baltabayev.studentservice.model.entity.Student;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "firstname", source = "firstname"),
            @Mapping(target = "lastname", source = "lastname"),
            @Mapping(target = "birthDate", source = "birthdate"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "gender", source = "gender"),
            @Mapping(target = "course", source = "course"),
            @Mapping(target = "gpa", source = "gpa"),
            @Mapping(target = "facultyId", source = "facultyId"),
            @Mapping(target = "avatar", source = "avatar")
    })
    Student toStudent(StudentRequest studentRequest);

    @InheritInverseConfiguration(name = "toStudent")
    StudentRequest toDto(Student student);
}
