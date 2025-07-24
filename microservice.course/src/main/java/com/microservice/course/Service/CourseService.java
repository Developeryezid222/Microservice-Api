package com.microservice.course.Service;

import com.microservice.course.Client.StudentClient;
import com.microservice.course.Controller.DTO.StudentDTO;
import com.microservice.course.Entity.Course;
import com.microservice.course.HTTP.Response.StudentCourseResponce;
import com.microservice.course.Persistence.ICourseRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private ICourseRespository courseRespository;
    @Autowired
    private StudentClient studentClient;

    public List<Course> getAllCourses() {
        return (List<Course>) courseRespository.findAll();
    }

    public ResponseEntity<Course> getCourseById(Long id) {
        Optional<Course> course = courseRespository.findById(id);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Course> createCourse(Course course) {
        Course savedCourse = courseRespository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    public ResponseEntity<Course> updateCourse(Long id, Course updatedCourse) {
        return courseRespository.findById(id)
                .map(course -> {
                    course.setName(updatedCourse.getName());
                    course.setTeacher(updatedCourse.getTeacher());
                    courseRespository.save(course);
                    return ResponseEntity.ok(course);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteCourse(Long id) {
        courseRespository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public StudentCourseResponce findStudentsByCourseId(Long idCourse) {

        // Consultar el curso
        Course course = courseRespository.findById(idCourse)
                .orElseThrow(() -> new IllegalStateException("Curso no encontrado con el ID:" + idCourse));

        //Obtener los estudiantes
        List<StudentDTO> studentDTOList = studentClient.findAllStudentByCourse(idCourse);
        return StudentCourseResponce.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentDTOList(studentDTOList)
                .build();
    }

}
