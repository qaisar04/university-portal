package kz.baltabayev.facultydepartmentservice.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import kz.baltabayev.facultydepartmentservice.model.entity.Department

data class FacultyDto(
    @NotNull @NotBlank
    var name: String? = null,
    @NotNull @NotBlank
    var dean: String? = null,
    var departments: List<Department> = listOf()
)