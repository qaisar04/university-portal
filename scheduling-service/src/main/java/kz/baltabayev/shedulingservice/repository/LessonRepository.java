package kz.baltabayev.shedulingservice.repository;

import kz.baltabayev.shedulingservice.model.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
