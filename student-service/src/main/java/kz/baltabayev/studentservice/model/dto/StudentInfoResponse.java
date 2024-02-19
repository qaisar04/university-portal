package kz.baltabayev.studentservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfoResponse {
    StudentRequest studentRequest;
    Map<Long, List<GradeResponse>> grades;
}
