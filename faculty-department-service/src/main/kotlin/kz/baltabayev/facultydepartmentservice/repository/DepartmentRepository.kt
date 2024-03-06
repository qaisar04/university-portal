package kz.baltabayev.facultydepartmentservice.repository

import kz.baltabayev.facultydepartmentservice.model.entity.Department
import org.springframework.data.jpa.repository.JpaRepository

interface DepartmentRepository: JpaRepository<Department, Long> {
}