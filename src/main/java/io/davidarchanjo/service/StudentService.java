package io.davidarchanjo.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.davidarchanjo.model.Student;
import io.davidarchanjo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public Student addNewStudent(Student student) {
        return repository.save(student);
    }

    @Cacheable(cacheNames = { "studentCache" }, key = "")
    public List<Student> findAllStudent() {
        longRunningProcess();
        return repository.findAll();
    }

    @Cacheable(cacheNames = { "studentCache" }, key = "#studentId")
    public Student findStudent(Long studentId) {
        longRunningProcess();
        return repository.findById(studentId)
            .orElse(null);
    }

    @CachePut(cacheNames = { "studentCache" }, key = "#student.studentId")
    public Student updateStudent(Student student) {
        return repository.save(student);
    }

    @CacheEvict(cacheNames = { "studentCache" }, key = "#studentId")
    public String deleteStudent(Long studentId) {
        repository.deleteById(studentId);
        return "record deleted successfully";
    }
    
    private void longRunningProcess() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
