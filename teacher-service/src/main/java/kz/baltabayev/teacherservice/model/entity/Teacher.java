package kz.baltabayev.teacherservice.model.entity;

import kz.baltabayev.teacherservice.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "teachers")
public class Teacher {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    @Field(name = "birth_date")
    private LocalDate birthDate;
    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private String avatar;
}
