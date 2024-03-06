package kz.baltabayev.facultydepartmentservice.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "department")
data class Department(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var head: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    var faculty: Faculty? = null
)
