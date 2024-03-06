package kz.baltabayev.facultydepartmentservice.service

import kz.baltabayev.facultydepartmentservice.model.entity.Department

interface DepartmentService {

    fun save(department: Department)

    fun update(department: Department)

    fun delete(id: Long)

    fun findById(id: Long): Department
}