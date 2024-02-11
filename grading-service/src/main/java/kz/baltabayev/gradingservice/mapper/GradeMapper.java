package kz.baltabayev.gradingservice.mapper;

import kz.baltabayev.gradingservice.model.dto.GradeDto;
import kz.baltabayev.gradingservice.model.entity.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GradeMapper {

    GradeDto toDto(Grade grade);

    @Mapping(target = "id", ignore = true)
    Grade toEntity(GradeDto gradeDto);
}
