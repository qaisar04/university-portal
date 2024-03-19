package kz.baltabayev.teacherservice.mapper;

import kz.baltabayev.teacherservice.model.dto.TeacherRequest;
import kz.baltabayev.teacherservice.model.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(target = "id", ignore = true)
    Teacher toEntity(TeacherRequest request);
}
