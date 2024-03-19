package kz.baltabayev.teacherservice.repository;

import kz.baltabayev.teacherservice.model.entity.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String> {
}
