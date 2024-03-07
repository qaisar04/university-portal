package kz.baltabayev.facultydepartmentservice.controller

import kz.baltabayev.facultydepartmentservice.model.dto.DepartmentDto
import kz.baltabayev.facultydepartmentservice.service.DepartmentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/department")
class DeparmentController(
    private var departmentService: DepartmentService
) {

    @GetMapping("{id}")
    fun get(@PathVariable id: Long) = departmentService.findById(id)

    @PutMapping("{id}")
    fun update(@RequestBody departmentDto: DepartmentDto, @PathVariable id: Long) {
        departmentService.update(departmentDto, id)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) {
        departmentService.delete(id)
    }

    @PostMapping
    fun save(@RequestBody departmentDto: DepartmentDto) {
        departmentService.save(departmentDto)
    }

    @GetMapping("{facultyId}/{departmentId}")
    fun isExist(@PathVariable facultyId: Long, @PathVariable departmentId: Long) =
        departmentService.isExists(departmentId, facultyId)
}