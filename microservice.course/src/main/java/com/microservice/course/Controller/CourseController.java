package com.microservice.course.Controller;




import com.microservice.course.Entity.Course;
import com.microservice.course.HTTP.Response.StudentCourseResponce;
import com.microservice.course.Service.CourseService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:52528/")
@RestController
@RequestMapping("/v1/courses")
public class CourseController {


    @Autowired
    private final CourseService courseService;

    // Constructor que permite la inyección explícita del servicio.
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Endpoint GET: retorna todos los cursos disponibles.
    // URL: http://localhost:9090/v1/courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Endpoint GET: busca un curso por su ID.
    // URL: /v1/courses/{id}
    // Retorna 200 OK si lo encuentra, o 404 si no.
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    // Endpoint POST: crea un nuevo curso.
    // URL: /v1/courses
    // Recibe un objeto Course en el cuerpo del request.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    // Endpoint PUT: actualiza un curso existente por ID.
    // URL: /v1/courses/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        return courseService.deleteCourse(id);
    }

    // Endpoint GET: busca estudiantes asociados a un curso.
    // URL: /v1/courses/search-student/{courseId}
    @GetMapping("/search-student/{courseId}")
    public ResponseEntity<StudentCourseResponce> findStudentsByCourseId(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.findStudentsByCourseId(courseId));
    }
}