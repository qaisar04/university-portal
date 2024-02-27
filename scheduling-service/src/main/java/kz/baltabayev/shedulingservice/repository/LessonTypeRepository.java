package kz.baltabayev.shedulingservice.repository;

import kz.baltabayev.shedulingservice.model.entity.LessonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonTypeRepository extends JpaRepository<LessonType, Long> {
}
