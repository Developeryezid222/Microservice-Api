package com.microservice.course.Controller;



import com.microservice.course.Controller.DTO.StudentDTO;
import com.microservice.course.Entity.Course;
import com.microservice.course.HTTP.Response.StudentCourseResponce;
import com.microservice.course.Service.CourseService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/course")
public class CourseController {

    @Autowired
    private final CourseService courseService;


    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        return courseService.deleteCourse(id);
    }

    @GetMapping("/search-student/{courseId}")
    public ResponseEntity<StudentCourseResponce> findStudentsByCourseId(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.findStudentsByCourseId(courseId));
    }
}