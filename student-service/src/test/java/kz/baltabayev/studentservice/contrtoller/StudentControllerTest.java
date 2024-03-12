package kz.baltabayev.studentservice.contrtoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kz.baltabayev.studentservice.controller.StudentController;
import kz.baltabayev.studentservice.model.dto.StudentRequest;
import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.model.enums.Gender;
import kz.baltabayev.studentservice.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    @MockBean
    private StudentService studentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void getAllStudents() throws Exception {
        Student s1 = Student.builder().firstname("qaisar").build();
        Student s2 = Student.builder().build();
        Student s3 = Student.builder().build();

        when(studentService.getAll()).thenReturn(List.of(s1, s2, s3));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname").value(s1.getFirstname()))
                .andExpect(jsonPath("length()").value(3));
        verify(studentService, times(1)).getAll();
    }

    @Test
    void createStudent() throws Exception {
        StudentRequest request = new StudentRequest(
                "qaisar", "baltabayev", LocalDate.of(2011, 11, 11), "test@test.io", Gender.MALE, 2, 1L, 1L
        );

        String requestJson = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/api/v1/students/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());
    }

//    @Test
//    void deleteStudent() throws Exception {
//        Student s1 = Student.builder().id(1L).firstname("qaisar").build();
//        when(studentService.get(1L)).thenReturn(s1);
//        mockMvc.perform(delete("/api/v1/students/delete/{id}", 1L))
//                .andExpect(status().isOk());
//    }
}
