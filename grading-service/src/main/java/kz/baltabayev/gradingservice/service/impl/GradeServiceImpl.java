package kz.baltabayev.gradingservice.service.impl;

import kz.baltabayev.gradingservice.exception.GradeNotFoundException;
import kz.baltabayev.gradingservice.model.dto.GradeDto;
import kz.baltabayev.gradingservice.model.entity.Grade;
import kz.baltabayev.gradingservice.repository.GradeRepository;
import kz.baltabayev.gradingservice.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    @Override
    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
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
    public void deleteByStudentId(Long studentId) {
        gradeRepository.deleteByStudentId(studentId);
    }

    @Override
    public Grade update(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public Map<Long, List<GradeDto>> getByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId)
                .stream()
                .collect(Collectors.groupingBy(Grade::getSubjectId,
                        Collectors.mapping(grade -> new GradeDto(grade.getScore()), Collectors.toList())));
    }

    @Override
    public Double getAverageScoreByStudentId(Long studentId) {
        return getByStudentId(studentId).values().stream()
                .flatMap(List::stream)
                .mapToDouble(GradeDto::score)
                .average()
                .orElse(0.0);
    }

    @Override
    public List<Grade> getByStudentIdAndSubjectId(Long studentId, Long subjectId) {
        return gradeRepository.findByStudentIdAndSubjectId(studentId, subjectId);
    }
}
