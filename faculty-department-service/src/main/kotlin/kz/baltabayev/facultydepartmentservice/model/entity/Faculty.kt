package kz.baltabayev.facultydepartmentservice.model.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "faculty")
data class Faculty(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var dean: String? = null,

    @JsonManagedReference
    @OneToMany(mappedBy = "faculty", cascade = [CascadeType.PERSIST, CascadeType.MERGE], fetch = FetchType.LAZY)
    var departments: List<Department> = mutableListOf()
)