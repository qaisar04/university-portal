package kz.baltabayev.gradingservice.mapper;

import kz.baltabayev.gradingservice.model.dto.GradeDto;
import kz.baltabayev.gradingservice.model.entity.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * Mapper interface for the Grade entity and its DTO.
 * This interface defines the mapping between the Grade entity and its DTO.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GradeMapper {

    /**
     * Converts a Grade entity to a GradeDto.
     *
     * @param grade the Grade entity to convert
     * @return the converted GradeDto
     */
    GradeDto toDto(Grade grade);

    /**
     * Converts a GradeDto to a Grade entity.
     * The ID of the Grade entity is ignored during the conversion.
     *
     * @param gradeDto the GradeDto to convert
     * @return the converted Grade entity
     */
    @Mapping(target = "id", ignore = true)
    Grade toEntity(GradeDto gradeDto);
}
