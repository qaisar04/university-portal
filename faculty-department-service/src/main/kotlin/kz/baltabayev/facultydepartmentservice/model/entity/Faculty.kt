package kz.baltabayev.facultydepartmentservice.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "faculty")
data class Faculty(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var dean: String? = null,

    @OneToMany
    var departments: List<Department> = listOf()
)