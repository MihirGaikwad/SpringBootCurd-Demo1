package com.springrest.springbootcrud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.springbootcrud.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    void delete(Optional<Student> existingStudent);
    
}
