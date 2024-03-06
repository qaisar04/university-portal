package kz.baltabayev.facultydepartmentservice.service.impl

import kz.baltabayev.facultydepartmentservice.exception.EntityNotFoundException
import kz.baltabayev.facultydepartmentservice.model.entity.Faculty
import kz.baltabayev.facultydepartmentservice.repository.FacultyRepository
import kz.baltabayev.facultydepartmentservice.service.FacultyService

class FacultyServiceImpl(
    private val facultyRepository: FacultyRepository
) : FacultyService {

    override fun save(faculty: Faculty) {
        facultyRepository.save(faculty)
    }

    override fun update(faculty: Faculty) {
        facultyRepository.save(faculty)
    }

    override fun delete(id: Long) {
        facultyRepository.deleteById(id)
    }

    override fun findById(id: Long): Faculty {
        return facultyRepository.findById(id)
            .orElseThrow { EntityNotFoundException(Faculty::class.java, id) }
    }
}