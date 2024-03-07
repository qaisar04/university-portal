package kz.baltabayev.studentservice.mapper;

import kz.baltabayev.studentservice.model.dto.StudentRequest;
import kz.baltabayev.studentservice.model.dto.StudentResponse;
import kz.baltabayev.studentservice.model.entity.Student;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "birthDate", source = "birthdate"),
    })
    Student toStudent(StudentResponse studentResponse);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "avatar", ignore = true),
            @Mapping(target = "gpa", ignore = true),
            @Mapping(target = "birthDate", source = "birthdate"),
    })
    Student toStudent(StudentRequest studentRequest);


    @InheritInverseConfiguration(name = "toStudent")
    StudentResponse toResponse(Student student);

    @Mapping(target = "birthdate", source = "birthDate")
    StudentRequest toRequest(Student student);
}
