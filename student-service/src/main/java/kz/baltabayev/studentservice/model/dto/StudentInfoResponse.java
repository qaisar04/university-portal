package kz.baltabayev.studentservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfoResponse {
    StudentRequest studentRequest;
    List<GradeResponse> gradeList;
}
