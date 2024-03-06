package kz.baltabayev.facultydepartmentservice.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class DepartmentDto(
    @NotNull @NotBlank
    var name: String,
    @NotNull @NotBlank
    var head: String,
    @NotNull @NotBlank
    var facultyId: Long
)