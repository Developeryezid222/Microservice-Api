package com.microservice.student;

import com.microservice.student.Entity.Student;
import com.microservice.student.Persistence.StudentRepository;
import com.microservice.student.Service.StudentServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImpTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImp studentService;

    @Test
    void testFinAll() {
        List<Student> mockList = List.of(new Student(1L, "Juan", "pepe","juanpe@gmail.com",11L));
        when(studentRepository.findAll()).thenReturn(mockList);

        List<Student> result = studentService.finAll();

        try {
            assertEquals(1, result.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals("Juan", result.get(0).getName());
    }

    @Test
    void testFinById_found() {
        Student student = new Student(2L, "Vale", "Ramírez", "vale@example.com", 10L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

            Student result = studentService.finById(1L);

            assertEquals("Vale", result.getName());

    }

    @Test
    void testFinById_notFound() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> studentService.finById(99L));
    }

    @Test
    void testSave() {
        Student student = new Student(3L, "Ana", "Haag", "anaha@gmail.com",8L);

        studentService.save(student);

        verify(studentRepository).save(student);
    }

    @Test
    void testFinByIdCourse() {
        Long courseId = 10L;
        List<Student> students = List.of(new Student(4L, "Carlos", "Hag", "carha@gmail.com",9L));
        when(studentRepository.findAllByCourseId(courseId)).thenReturn(students);

        List<Student> result = studentService.finByIdCourse(courseId);

        assertFalse(result.isEmpty());
        assertEquals("Carlos", result.get(0).getName());
    }
}
