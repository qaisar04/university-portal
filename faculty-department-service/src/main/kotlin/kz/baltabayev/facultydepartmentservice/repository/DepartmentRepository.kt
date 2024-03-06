package kz.baltabayev.facultydepartmentservice.repository

import kz.baltabayev.facultydepartmentservice.model.entity.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository: JpaRepository<Department, Long> {
}