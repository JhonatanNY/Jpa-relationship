package com.example.springboot.springboot_jpa_relationship.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.springboot_jpa_relationship.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{

}
