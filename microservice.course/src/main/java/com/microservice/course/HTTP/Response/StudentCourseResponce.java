package com.microservice.course.HTTP.Response;


import com.microservice.course.Controller.DTO.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCourseResponce {
   private String courseName;
   private String teacher;
   private List<StudentDTO> studentDTOList;

}
