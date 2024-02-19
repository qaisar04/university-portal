package kz.baltabayev.subjectservice.service.impl;

import kz.baltabayev.subjectservice.exception.SubjectNotFoundException;
import kz.baltabayev.subjectservice.model.entity.Subject;
import kz.baltabayev.subjectservice.repository.SubjectRepository;
import kz.baltabayev.subjectservice.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public Subject getById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException(id));
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Set<Subject> getByTeacherId(Set<Long> teacherId) {
        return null;
    }
}
