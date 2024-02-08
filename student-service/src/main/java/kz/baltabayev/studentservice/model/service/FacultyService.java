package kz.baltabayev.studentservice.model.service;

import kz.baltabayev.studentservice.model.entity.Faculty;

import java.util.List;

public interface FacultyService {

    List<Faculty> getAll();

    Faculty getById(Long id);

    Faculty create(Faculty faculty);

    Faculty update(Faculty faculty);

    void deleteFacultyById(Long id);
}
