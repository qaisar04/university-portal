package kz.baltabayev.facultydepartmentservice.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


data class DepartmentDto(
    @NotNull @NotBlank
    var name: String? = null,
    @NotNull @NotBlank
    var head: String? = null,
)