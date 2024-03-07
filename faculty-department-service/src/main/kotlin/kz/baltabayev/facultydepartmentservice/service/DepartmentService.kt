package kz.baltabayev.facultydepartmentservice.service

import kz.baltabayev.facultydepartmentservice.model.dto.DepartmentDto
import kz.baltabayev.facultydepartmentservice.model.entity.Department

interface DepartmentService {

    fun save(departmentDto: DepartmentDto)

    fun update(departmentDto: DepartmentDto, id: Long)

    fun delete(id: Long)

    fun findById(id: Long): Department

    fun isExists(id: Long, facultyId: Long): Boolean
}