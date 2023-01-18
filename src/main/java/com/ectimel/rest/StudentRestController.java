package com.ectimel.rest;

import com.ectimel.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> students;

    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();
        students.add(new Student("Dominik", "Tworek", 3));
        students.add(new Student("Dominik", "zero", 0));
        students.add(new Student("Rafal", "Tworek", 1));
        students.add(new Student("Wojtek", "Wedzicha", 2));

        students.sort(Comparator.comparing(Student::getId));
    }

    @GetMapping("/students")
    public List<Student> studentsList() {

        return students;
    }

    @GetMapping("students/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {

        if (studentId >= students.size() || studentId < 0) {
            throw new StudentNotFoundException("Student id " + studentId + " not found");
        }

        return students.get(studentId);
    }



}
