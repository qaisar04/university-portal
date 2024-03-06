package kz.baltabayev.facultydepartmentservice.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class FacultyDto(
    @NotNull @NotBlank
    var name: String? = null,
    @NotNull @NotBlank
    var dean: String? = null,
)