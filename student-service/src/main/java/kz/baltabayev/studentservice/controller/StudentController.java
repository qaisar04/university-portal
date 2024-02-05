package kz.baltabayev.studentservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @RequestMapping("/status/check")
    public String status() {
        return "Working";
    }
}
