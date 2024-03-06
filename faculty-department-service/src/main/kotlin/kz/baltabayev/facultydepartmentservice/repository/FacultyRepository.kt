package kz.baltabayev.facultydepartmentservice.repository

import kz.baltabayev.facultydepartmentservice.model.entity.Faculty
import org.springframework.data.jpa.repository.JpaRepository

interface FacultyRepository: JpaRepository<Faculty, Long> {
}