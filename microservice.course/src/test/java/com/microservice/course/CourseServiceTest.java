package com.microservice.course;



import com.microservice.course.Client.StudentClient;
import com.microservice.course.Controller.DTO.StudentDTO;
import com.microservice.course.Entity.Course;
import com.microservice.course.HTTP.Response.StudentCourseResponce;
import com.microservice.course.Persistence.ICourseRespository;
import com.microservice.course.Service.CourseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private ICourseRespository courseRepository;

    @Mock
    private StudentClient studentClient;

    @InjectMocks
    private CourseService courseService;

    @Test
    @DisplayName("Debería retornar todos los cursos")
    void testGetAllCourses() {
        List<Course> mockCourses = List.of(new Course(1L, "Java", "Juan"));
        when(courseRepository.findAll()).thenReturn(mockCourses);

        List<Course> result = courseService.getAllCourses();

        assertEquals(1, result.size());
        assertEquals("Java", result.get(0).getName());
    }

    @Test
    @DisplayName("Debería retornar curso existente por ID")
    void testGetCourseById_found() {
        Course course = new Course(2L, "Spring", "Ana");
        when(courseRepository.findById(2L)).thenReturn(Optional.of(course));

        ResponseEntity<Course> response = courseService.getCourseById(2L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Spring", response.getBody().getName());
    }

    @Test
    @DisplayName("Debería retornar 404 si no se encuentra el curso por ID")
    void testGetCourseById_notFound() {
        when(courseRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Course> response = courseService.getCourseById(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Debería guardar nuevo curso correctamente")
    void testCreateCourse() {
        Course newCourse = new Course(null, "Docker", "Luis");
        Course savedCourse = new Course(3L, "Docker", "Luis");

        when(courseRepository.save(newCourse)).thenReturn(savedCourse);

        ResponseEntity<Course> response = courseService.createCourse(newCourse);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Docker", response.getBody().getName());
    }

    @Test
    @DisplayName("Debería actualizar un curso existente")
    void testUpdateCourse_found() {
        Course original = new Course(4L, "Kafka", "Mario");
        Course update = new Course(null, "Kafka Avanzado", "María");

        when(courseRepository.findById(4L)).thenReturn(Optional.of(original));
        when(courseRepository.save(any())).thenReturn(original);

        ResponseEntity<Course> response = courseService.updateCourse(4L, update);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Kafka Avanzado", response.getBody().getName());
    }

    @Test
    @DisplayName("Debería retornar 404 al intentar actualizar curso no existente")
    void testUpdateCourse_notFound() {
        Course update = new Course(null, "Nuevo", "Nuevo");

        when(courseRepository.findById(100L)).thenReturn(Optional.empty());

        ResponseEntity<Course> response = courseService.updateCourse(100L, update);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Debería eliminar un curso sin errores")
    void testDeleteCourse() {
        doNothing().when(courseRepository).deleteById(1L);

        ResponseEntity<Void> response = courseService.deleteCourse(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(courseRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Debería retornar estudiantes de un curso existente")
    void testFindStudentsByCourseId() {
        Course course = new Course(5L, "Microservicios", "Raúl");
        List<StudentDTO> students = List.of(new StudentDTO("Ana", "marcela", "ana@gmail.com", 5L));

        when(courseRepository.findById(5L)).thenReturn(Optional.of(course));
        when(studentClient.findAllStudentByCourse(5L)).thenReturn(students);

        StudentCourseResponce response = courseService.findStudentsByCourseId(5L);

        assertEquals("Microservicios", response.getCourseName());
        assertEquals("Raúl", response.getTeacher());
        assertEquals(1, response.getStudentDTOList().size());
        assertEquals("Ana", response.getStudentDTOList().get(0).getName());
    }

    @Test
    @DisplayName("Debería lanzar excepción si no se encuentra el curso")
    void testFindStudentsByCourseId_courseNotFound() {
        when(courseRepository.findById(77L)).thenReturn(Optional.empty());

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            courseService.findStudentsByCourseId(77L);
        });

        assertEquals("Curso no encontrado con el ID:77", exception.getMessage());
    }
}