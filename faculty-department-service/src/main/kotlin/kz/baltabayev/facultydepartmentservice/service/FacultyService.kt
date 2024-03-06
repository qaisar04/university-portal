package kz.baltabayev.facultydepartmentservice.service

import kz.baltabayev.facultydepartmentservice.model.entity.Faculty

interface FacultyService {

    fun save(faculty: Faculty)

    fun update(faculty: Faculty)

    fun delete(id: Long)

    fun findById(id: Long): Faculty
}