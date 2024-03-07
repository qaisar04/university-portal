package kz.baltabayev.facultydepartmentservice.service.impl

import kz.baltabayev.facultydepartmentservice.exception.EntityNotFoundException
import kz.baltabayev.facultydepartmentservice.mapper.DepartmentMapper
import kz.baltabayev.facultydepartmentservice.model.dto.DepartmentDto
import kz.baltabayev.facultydepartmentservice.model.dto.DepartmentResponse
import kz.baltabayev.facultydepartmentservice.model.entity.Department
import kz.baltabayev.facultydepartmentservice.repository.DepartmentRepository
import kz.baltabayev.facultydepartmentservice.service.DepartmentService
import org.springframework.stereotype.Service

@Service
class DepartmentServiceImpl(
    private var departmentRepository: DepartmentRepository,
    private var departmentMapper: DepartmentMapper
) : DepartmentService {

    override fun save(departmentDto: DepartmentDto) {
        val entity = departmentMapper.toEntity(departmentDto)
        departmentRepository.save(entity)
    }

    override fun update(departmentDto: DepartmentDto, id: Long) {
        val existingDepartment = findById(id)
        val updatedDepartment = departmentMapper.toEntity(departmentDto)
        updatedDepartment.id = existingDepartment.id
        departmentRepository.save(updatedDepartment)
    }

    override fun delete(id: Long) {
        departmentRepository.deleteById(id);
    }

    override fun get(id: Long): DepartmentResponse {
        return departmentMapper.toResponse(findById(id))
    }

    fun findById(id: Long): Department {
        return departmentRepository.findById(id)
            .orElseThrow { EntityNotFoundException(Department::class.java, id) }
    }

    override fun isExists(id: Long, facultyId: Long): Boolean {
        val department = findById(id)
        return department.faculty?.id?.equals(facultyId) ?: false
    }
}