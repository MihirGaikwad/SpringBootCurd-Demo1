package com.springrest.springbootcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springbootcrud.entity.Student;
import com.springrest.springbootcrud.exception.ResourceNotFoundException;
import com.springrest.springbootcrud.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class mycontroller{

    @Autowired
    private StudentRepository studentRepository;

    // get all students
    @GetMapping
    public List<Student> getAllStudents(){        
        return this.studentRepository.findAll();   
    }

    // get student by id
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable(value = "id") long StudentId){
        return this.studentRepository.findById(StudentId)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found with this id :" + StudentId));
    }    


    // create student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return this.studentRepository.save(student);
    }

    // update student
    @PutMapping("/{id}")
    public Student updatStudent(@RequestBody Student student, @PathVariable ("id") long StudentId){
        Student existingStudent = this.studentRepository.findById(StudentId)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found with this id :" + StudentId));
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail()); 
        return this.studentRepository.save(existingStudent);      
    }

    // delete student by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable ("id") long StudentId){
        Student existingStudent = this.studentRepository.findById(StudentId)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found with this id :" + StudentId));
        this.studentRepository.delete(existingStudent);
        return ResponseEntity.ok().build();

    }

    
}
