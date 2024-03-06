package kz.baltabayev.facultydepartmentservice.controller

import kz.baltabayev.facultydepartmentservice.model.dto.FacultyDto
import kz.baltabayev.facultydepartmentservice.service.FacultyService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/faculty")
class FacultyController(
    private var facultyService: FacultyService
) {

    @GetMapping("{id}")
    fun get(@PathVariable id: Long) = facultyService.findById(id)

    @PostMapping
    fun save(@RequestBody facultyDto: FacultyDto) {
        facultyService.save(facultyDto)
    }

    @PutMapping("{id}")
    fun update(@RequestBody facultyDto: FacultyDto, @PathVariable id: Long) {
        facultyService.update(facultyDto, id)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) {
        facultyService.delete(id)
    }
}