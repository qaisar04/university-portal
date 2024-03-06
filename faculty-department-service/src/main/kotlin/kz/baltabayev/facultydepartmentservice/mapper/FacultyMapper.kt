package kz.baltabayev.facultydepartmentservice.mapper

import kz.baltabayev.facultydepartmentservice.model.dto.FacultyDto
import kz.baltabayev.facultydepartmentservice.model.entity.Faculty
import org.springframework.stereotype.Component

@Component
class FacultyMapper{

    fun toEntity(dto: FacultyDto): Faculty {
        return Faculty(
            name = dto.name,
            dean = dto.dean
        )
    }

    fun toDto(entity: Faculty): FacultyDto {
        return FacultyDto(
            name = entity.name,
            dean = entity.dean
        )
    }
}