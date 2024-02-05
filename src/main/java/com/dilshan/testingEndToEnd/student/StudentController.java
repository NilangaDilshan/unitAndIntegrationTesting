package com.dilshan.testingEndToEnd.student;

import com.dilshan.testingEndToEnd.student.entities.Student;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public ResponseEntity<?> addStudent(
            @Valid
            @RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<?> deleteStudent(
            @PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok(HttpEntity.EMPTY);

    }
}
