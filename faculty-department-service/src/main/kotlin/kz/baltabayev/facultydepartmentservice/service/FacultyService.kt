package kz.baltabayev.facultydepartmentservice.service

import kz.baltabayev.facultydepartmentservice.model.dto.FacultyDto
import kz.baltabayev.facultydepartmentservice.model.entity.Faculty

interface FacultyService {

    fun save(facultyDto: FacultyDto)

    fun update(facultyDto: FacultyDto, id: Long)

    fun delete(id: Long)

    fun findById(id: Long): Faculty
}