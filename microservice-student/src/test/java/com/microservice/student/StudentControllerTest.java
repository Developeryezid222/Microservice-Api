package com.microservice.student;


import com.microservice.student.Controller.StudentController;
import com.microservice.student.Entity.Student;
import com.microservice.student.Service.IStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@AutoConfigureMockMvc
@SpringBootTest
class StudentControllerTest {

    @Mock
    private IStudentService iStudentService;

    @InjectMocks
    private StudentController studentController;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");
        student.setCourseId(101L);
    }

    @Test
    void testFindAllStudent() {
        List<Student> students = Arrays.asList(student);
        when (iStudentService.finAll()).thenReturn(students);

        List<Student> result = studentController.findAllStudent();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        verify(iStudentService, times(1)).finAll();
    }

    @Test
    void testSaveStudent() {
        doNothing().when(iStudentService).save(any(Student.class));

        studentController.saveStudent(student);

        verify(iStudentService, times(1)).save(student);
    }

    @Test
    void testFinById() {
        when(iStudentService.finById(1L)).thenReturn(student);

        ResponseEntity<Student> response = studentController.finById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        verify(iStudentService, times(1)).finById(1L);
    }

    @Test
    void testFindAllByCourseId() {
        List<Student> students = Arrays.asList(student);
        when(iStudentService.finByIdCourse(101L)).thenReturn(students);

        ResponseEntity<List<Student>> response = studentController.findAllByCourseId(101L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(101L, response.getBody().get(0).getCourseId());
        verify(iStudentService, times(1)).finByIdCourse(101L);
    }
}
