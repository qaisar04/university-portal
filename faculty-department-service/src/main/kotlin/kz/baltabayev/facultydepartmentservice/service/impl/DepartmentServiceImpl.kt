package kz.baltabayev.facultydepartmentservice.service.impl

import jakarta.persistence.EntityNotFoundException
import kz.baltabayev.facultydepartmentservice.model.entity.Department
import kz.baltabayev.facultydepartmentservice.repository.DepartmentRepository
import kz.baltabayev.facultydepartmentservice.service.DepartmentService
import org.springframework.stereotype.Service

@Service
class DepartmentServiceImpl(
    private var departmentRepository: DepartmentRepository
) : DepartmentService {

    override fun save(department: Department) {
        departmentRepository.save(department)
    }

    override fun update(department: Department) {
        departmentRepository.save(department)
    }

    override fun delete(id: Long) {
        departmentRepository.deleteById(id);
    }

    override fun findById(id: Long): Department {
        return departmentRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Department with id %s not found ".format(id)) }
    }
}