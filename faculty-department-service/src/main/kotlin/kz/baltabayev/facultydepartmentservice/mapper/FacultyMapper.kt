package kz.baltabayev.facultydepartmentservice.mapper

import kz.baltabayev.facultydepartmentservice.model.dto.FacultyDto
import kz.baltabayev.facultydepartmentservice.model.entity.Faculty
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface FacultyMapper {

    @Mapping(target = "id", ignore = true)
    fun toEntity(dto: FacultyDto): Faculty

    fun toDto(entity: Faculty): FacultyDto
}