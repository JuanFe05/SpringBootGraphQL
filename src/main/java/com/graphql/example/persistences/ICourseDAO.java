package com.graphql.example.persistences;

import com.graphql.example.domain.entites.Course;
import org.springframework.data.repository.CrudRepository;

public interface ICourseDAO extends CrudRepository<Course, Long> {
}
