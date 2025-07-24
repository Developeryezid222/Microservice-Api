package com.microservice.student.Service;

import com.microservice.student.Entity.Student;
import com.microservice.student.Persistence.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements IStudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public List<Student> finAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student finById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Student student) {
          studentRepository.save(student);

    }

    @Override
    public List<Student> finByIdCourse(Long courseId) {
        return studentRepository.findAllByCourseId(courseId);
    }
}
