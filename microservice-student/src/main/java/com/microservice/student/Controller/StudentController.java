package com.microservice.student.Controller;


import com.microservice.student.Entity.Student;
import com.microservice.student.Service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/students")
public class StudentController {

    @Autowired
    private final IStudentService iStudentService;


    public StudentController(IStudentService iStudentService) {
        this.iStudentService = iStudentService;
    }

    // Endpoint GET: retorna todos los estudiantes del sistema.
    // URL: /v1/students
    @GetMapping
    public List<Student> findAllStudent(){
        return iStudentService.finAll();
    }


    // Endpoint POST: guarda un nuevo estudiante.
    // URL: /v1/students
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody Student student) {
        iStudentService.save(student);
    }


    @GetMapping("search/{id}")
    public ResponseEntity<Student> finById(@PathVariable Long id) {
        return ResponseEntity.ok(iStudentService.finById(id));
    }

    // Endpoint GET: lista todos los estudiantes asociados a un curso por su ID.
    // URL: /v1/students/search-course/{courseId}
    @GetMapping("/search-course/{courseId}")
    public ResponseEntity<List<Student>> findAllByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(iStudentService.finByIdCourse(courseId));
    }
}
