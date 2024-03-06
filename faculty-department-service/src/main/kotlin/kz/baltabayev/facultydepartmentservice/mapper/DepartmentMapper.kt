package kz.baltabayev.facultydepartmentservice.mapper

import kz.baltabayev.facultydepartmentservice.model.dto.DepartmentDto
import kz.baltabayev.facultydepartmentservice.model.entity.Department
import kz.baltabayev.facultydepartmentservice.service.FacultyService
import org.springframework.stereotype.Component

@Component
class DepartmentMapper(
    private val facultyService: FacultyService
) {

    fun toEntity(departmentDto: DepartmentDto): Department {
        val faculty = facultyService.findById(departmentDto.facultyId)

        return Department(
            name = departmentDto.name,
            head = departmentDto.head,
            faculty = faculty
        )
    }

    fun toDto(department: Department?): DepartmentDto? {
        return department?.let {
            DepartmentDto(
                it.name!!,
                it.head!!,
                it.faculty?.id!!
            )
        }
    }
}
