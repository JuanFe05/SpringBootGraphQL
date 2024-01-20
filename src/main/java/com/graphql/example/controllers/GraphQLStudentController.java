package com.graphql.example.controllers;

import com.graphql.example.domain.entites.Course;
import com.graphql.example.domain.entites.Student;
import com.graphql.example.graphql.InputStudent;
import com.graphql.example.service.ICourseService;
import com.graphql.example.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class GraphQLStudentController {;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ICourseService courseService;

    @QueryMapping(name = "findStudentById")
    public Student findById(@Argument(name = "studentId") String id) {
        Long studentId = Long.parseLong(id);
        return studentService.findById(studentId);
    }

    @QueryMapping(name = "findAllStudents")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @MutationMapping(name = "createStudent")
    public Student createStudent(@Argument InputStudent inputStudent) {

        Course course = courseService.findById(Long.parseLong(inputStudent.getCourseId()));

        Student student = new Student();
        student.setFirstName(inputStudent.getFirstName());
        student.setLastName(inputStudent.getLastName());

        // Parsear de cadena de texto a un objeto de Fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition position = new ParsePosition(0);
        Date date = dateFormat.parse(inputStudent.getDateBirth(), position);

        student.setDateBirth(date);

        student.setCourse(course);

        studentService.createStudent(student);

        return student;
    }

    @MutationMapping(name = "deleteStudentById")
    public String deleteById(@Argument(name = "studentId") String id) {
        studentService.deleteById(Long.parseLong(id));

        return "Estudiante con " + id + " ha sido eliminado";
    }

}
