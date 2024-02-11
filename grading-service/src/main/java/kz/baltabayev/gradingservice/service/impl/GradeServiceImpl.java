package kz.baltabayev.gradingservice.service.impl;

import kz.baltabayev.gradingservice.client.StudentServiceClient;
import kz.baltabayev.gradingservice.exception.GradeNotFoundException;
import kz.baltabayev.gradingservice.model.entity.Grade;
import kz.baltabayev.gradingservice.repository.GradeRepository;
import kz.baltabayev.gradingservice.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentServiceClient studentServiceClient;

    @Override
    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade getById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new GradeNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        gradeRepository.deleteById(id);
    }

    @Override
    public Grade update(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public List<Grade> getByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public Double getAverageScoreByStudentId(Long studentId) {
        return getByStudentId(studentId).stream()
                .mapToDouble(Grade::getScore)
                .average()
                .orElse(0.0);
    }

    @Override
    public List<Grade> getByStudentIdAndSubjectId(Long studentId, Long subjectId) {
        return gradeRepository.findByStudentIdAndSubjectId(studentId, subjectId);
    }
}
