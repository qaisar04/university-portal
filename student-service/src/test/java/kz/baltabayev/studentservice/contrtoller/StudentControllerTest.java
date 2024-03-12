package kz.baltabayev.studentservice.contrtoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kz.baltabayev.studentservice.controller.StudentController;
import kz.baltabayev.studentservice.model.dto.StudentRequest;
import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.model.enums.Gender;
import kz.baltabayev.studentservice.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void getAllStudentsTest() throws Exception {
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
    void createStudentTest() throws Exception {
        StudentRequest request = new StudentRequest(
                "qaisar", "baltabayev", LocalDate.of(2011, 11, 11), "test@test.io", Gender.MALE, 2, 1L, 1L
        );
        String requestJson = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/api/v1/students/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());

//        verify(studentService, times(1)).save(request);
    }
}
