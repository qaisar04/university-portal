package kz.baltabayev.facultydepartmentservice.repository

import kz.baltabayev.facultydepartmentservice.model.entity.Faculty
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FacultyRepository: JpaRepository<Faculty, Long> {
}