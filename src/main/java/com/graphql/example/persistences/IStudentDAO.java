package com.graphql.example.persistences;

import com.graphql.example.domain.entites.Student;
import org.springframework.data.repository.CrudRepository;

public interface IStudentDAO extends CrudRepository<Student, Long> {
}
