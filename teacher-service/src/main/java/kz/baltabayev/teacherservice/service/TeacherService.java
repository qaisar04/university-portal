package kz.baltabayev.teacherservice.service;

import kz.baltabayev.teacherservice.model.dto.TeacherRequest;
import kz.baltabayev.teacherservice.model.entity.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * This interface defines the contract for the TeacherService.
 * It provides methods for managing teachers in the system.
 */
public interface TeacherService {

    /**
     * Saves a new teacher in the system.
     *
     * @param request the teacher request DTO containing the details of the teacher to be saved.
     * @return the saved teacher.
     */
    Teacher save(TeacherRequest request);

    /**
     * Uploads an avatar for a teacher.
     *
     * @param id the id of the teacher.
     * @param file the avatar file to be uploaded.
     */
    void uploadAvatar(String id, MultipartFile file);

    /**
     * Deletes the avatar of a teacher.
     *
     * @param id the id of the teacher whose avatar is to be deleted.
     */
    void deleteAvatar(String id);

    /**
     * Retrieves a teacher by id.
     *
     * @param id the id of the teacher to be retrieved.
     * @return the teacher with the given id.
     */
    Teacher get(String id);

    /**
     * Deletes a teacher by id.
     *
     * @param id the id of the teacher to be deleted.
     */
    void delete(String id);

    /**
     * Retrieves all teachers in the system.
     *
     * @return a list of all teachers.
     */
    List<Teacher> getAll();

    /**
     * Updates a teacher's details.
     *
     * @param request the teacher request DTO containing the updated details of the teacher.
     * @param id the id of the teacher to be updated.
     * @return the updated teacher.
     */
    Teacher update(TeacherRequest request, String id);
}