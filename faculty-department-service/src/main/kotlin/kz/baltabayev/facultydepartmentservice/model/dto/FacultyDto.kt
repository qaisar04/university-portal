package kz.baltabayev.facultydepartmentservice.model.dto

import kz.baltabayev.facultydepartmentservice.model.entity.Department

data class FacultyDto(
    var name: String? = null,
    var dean: String? = null,
    var departments: List<Department> = listOf()
)