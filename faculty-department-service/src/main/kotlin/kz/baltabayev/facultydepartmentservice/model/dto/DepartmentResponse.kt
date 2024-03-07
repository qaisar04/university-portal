package kz.baltabayev.facultydepartmentservice.model.dto

data class DepartmentResponse(
    var id: Long? = null,
    var name: String? = null,
    var head: String? = null,
    var facultyId: Long? = null
)