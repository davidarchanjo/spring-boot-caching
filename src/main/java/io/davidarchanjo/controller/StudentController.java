package io.davidarchanjo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.davidarchanjo.model.Student;
import io.davidarchanjo.service.StudentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(service.addNewStudent(student), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> retriveAllStudents() {
        return new ResponseEntity<>(service.findAllStudent(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> retriveStudent(@PathVariable Long id) {
        return new ResponseEntity<>(service.findStudent(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        return new ResponseEntity<>(service.updateStudent(student), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return new ResponseEntity<>(service.deleteStudent(id), HttpStatus.OK);
    }

}
