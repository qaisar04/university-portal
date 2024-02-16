package kz.baltabayev.gradingservice.service.impl;

import kz.baltabayev.gradingservice.exception.GradeNotFoundException;
import kz.baltabayev.gradingservice.mapper.GradeMapper;
import kz.baltabayev.gradingservice.model.dto.GradeDto;
import kz.baltabayev.gradingservice.model.dto.GradeResponse;
import kz.baltabayev.gradingservice.model.entity.Grade;
import kz.baltabayev.gradingservice.repository.GradeRepository;
import kz.baltabayev.gradingservice.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the GradeService interface.
 * This service provides CRUD operations for the Grade entity.
 */
@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    /**
     * Saves a grade in the database.
     *
     * @param grade the grade to save
     * @return the saved grade as a DTO
     */
    @Override
    public GradeDto save(Grade grade) {
        Grade saved = gradeRepository.save(grade);
        return gradeMapper.toDto(saved);
    }

    /**
     * Retrieves a grade by its ID.
     *
     * @param id the ID of the grade
     * @return the grade
     * @throws GradeNotFoundException if no grade with the given ID is found
     */
    public Grade getById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new GradeNotFoundException(id));
    }

    /**
     * Deletes a grade by its ID.
     *
     * @param id the ID of the grade
     */
    @Override
    public void deleteById(Long id) {
        gradeRepository.deleteById(id);
    }

    /**
     * Deletes all grades for a specific student.
     *
     * @param studentId the ID of the student
     */
    @Override
    public void deleteByStudentId(Long studentId) {
        gradeRepository.deleteByStudentId(studentId);
    }

    /**
     * Updates a grade in the database.
     *
     * @param grade the grade to update
     * @return the updated grade
     */
    @Override
    public Grade update(Grade grade) {
        return gradeRepository.save(grade);
    }

    /**
     * Retrieves all grades for a specific student, grouped by subject.
     *
     * @param studentId the ID of the student
     * @return a map where the key is the subject ID and the value is a list of grades for that subject
     */
    @Override
    public Map<Long, List<GradeResponse>> getByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId)
                .stream()
                .collect(Collectors.groupingBy(Grade::getSubjectId,
                        Collectors.mapping(grade -> new GradeResponse(grade.getScore()), Collectors.toList())));
    }

    /**
     * Calculates the average score for a specific student.
     *
     * @param studentId the ID of the student
     * @return the average score, or 0.0 if the student has no grades
     */
    @Override
    public Double getAverageScoreByStudentId(Long studentId) {
        return getByStudentId(studentId).values().stream()
                .flatMap(List::stream)
                .mapToDouble(GradeResponse::score)
                .average()
                .orElse(0.0);
    }

    /**
     * Retrieves all grades for a specific student and subject.
     *
     * @param studentId the ID of the student
     * @param subjectId the ID of the subject
     * @return a list of grades for the student and subject
     */
    @Override
    public List<Grade> getByStudentIdAndSubjectId(Long studentId, Long subjectId) {
        return gradeRepository.findByStudentIdAndSubjectId(studentId, subjectId);
    }
}