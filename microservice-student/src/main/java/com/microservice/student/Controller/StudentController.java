package com.microservice.student.Controller;


import com.microservice.student.Entity.Student;
import com.microservice.student.Service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/student")
public class StudentController {

    @Autowired
    private final IStudentService iStudentService;


    public StudentController(IStudentService iStudentService) {
        this.iStudentService = iStudentService;
    }

    @GetMapping
    public List<Student> findAllStudent(){
        return iStudentService.finAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody Student student) {
        iStudentService.save(student);
    }

    @GetMapping("search/{id}")
    public ResponseEntity<Student> finById(@PathVariable Long id) {
        return ResponseEntity.ok(iStudentService.finById(id));
    }

    @GetMapping("/search-course/{courseId}")
    public ResponseEntity<List<Student>> findAllByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(iStudentService.finByIdCourse(courseId));
    }

}
