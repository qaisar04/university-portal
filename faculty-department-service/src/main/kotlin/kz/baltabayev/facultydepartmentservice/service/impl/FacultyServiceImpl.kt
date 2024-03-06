package kz.baltabayev.facultydepartmentservice.service.impl

import kz.baltabayev.facultydepartmentservice.exception.EntityNotFoundException
import kz.baltabayev.facultydepartmentservice.mapper.FacultyMapper
import kz.baltabayev.facultydepartmentservice.model.dto.FacultyDto
import kz.baltabayev.facultydepartmentservice.model.entity.Faculty
import kz.baltabayev.facultydepartmentservice.repository.FacultyRepository
import kz.baltabayev.facultydepartmentservice.service.FacultyService
import org.springframework.stereotype.Service

@Service
class FacultyServiceImpl(
    private val facultyRepository: FacultyRepository,
    private val facultyMapper: FacultyMapper
) : FacultyService {

    override fun save(facultyDto: FacultyDto) {
        val faculty = facultyMapper.toEntity(facultyDto)
        facultyRepository.save(faculty)
    }

    override fun update(facultyDto: FacultyDto, id: Long) {
        val existingFaculty = findById(id)
        val updatedFaculty = facultyMapper.toEntity(facultyDto)
        updatedFaculty.id = existingFaculty.id
        facultyRepository.save(updatedFaculty)
    }

    override fun delete(id: Long) {
        facultyRepository.deleteById(id)
    }

    override fun findById(id: Long): Faculty {
        return facultyRepository.findById(id)
            .orElseThrow { EntityNotFoundException(Faculty::class.java, id) }
    }
}