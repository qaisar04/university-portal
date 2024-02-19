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
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "studentInfo.firstname", source = "name"),
            @Mapping(target = "studentInfo.lastname", source = "lasname"),
            @Mapping(target = "studentInfo.birthDate", source = "birthdate"),
            @Mapping(target = "studentInfo.gender", source = "gender"),
            @Mapping(target = "studentInfo.course", source = "course"),
            @Mapping(target = "studentInfo.gpa", source = "gpa"),
            @Mapping(target = "studentInfo.facultyId", source = "facultyId")
    })
    Student toStudent(StudentRequest studentRequest);

    @InheritInverseConfiguration(name = "toStudent")
    StudentRequest toDto(Student student);
}
