package kz.baltabayev.facultydepartmentservice.mapper

import kz.baltabayev.facultydepartmentservice.model.dto.DepartmentDto
import kz.baltabayev.facultydepartmentservice.model.entity.Department
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface DepartmentMapper {

    @Mapping(target = "id", ignore = true)
    fun toDto(entity: Department): Department

    fun toEntity(dto: DepartmentDto): Department
}