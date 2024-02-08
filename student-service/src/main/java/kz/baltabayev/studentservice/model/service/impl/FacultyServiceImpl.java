package kz.baltabayev.studentservice.model.service.impl;

import kz.baltabayev.studentservice.model.entity.Faculty;
import kz.baltabayev.studentservice.model.service.FacultyService;
import kz.baltabayev.studentservice.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Override
    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty getById(Long id) {
        return facultyRepository.findById(id).orElseThrow(() -> new RuntimeException("Faculty not found"));
    }

    @Override
    public Faculty create(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty update(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFacultyById(Long id) {
        facultyRepository.deleteById(id);
    }
}
