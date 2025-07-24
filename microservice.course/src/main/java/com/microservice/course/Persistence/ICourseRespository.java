package com.microservice.course.Persistence;


import com.microservice.course.Entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRespository extends CrudRepository<Course, Long> {

}
