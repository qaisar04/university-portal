package kz.baltabayev.facultydepartmentservice.controller

import kz.baltabayev.facultydepartmentservice.model.dto.DepartmentDto
import kz.baltabayev.facultydepartmentservice.service.DepartmentService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}