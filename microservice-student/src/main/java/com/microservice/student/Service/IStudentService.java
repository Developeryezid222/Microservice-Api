package com.microservice.student.Service;

import com.microservice.student.Entity.Student;

import java.util.List;

public interface IStudentService {

    List<Student> finAll();

    Student finById(Long id);

    void save(Student student);

    List<Student> finByIdCourse(Long courseId);


}
