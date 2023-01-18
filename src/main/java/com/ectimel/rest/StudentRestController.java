package com.ectimel.rest;

import com.ectimel.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> students;

    @PostConstruct
    public void loadData(){
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
        return students.get(studentId);
    }

}
