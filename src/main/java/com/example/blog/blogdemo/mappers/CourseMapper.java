package com.example.blog.blogdemo.mappers;

import com.example.blog.blogdemo.DTO.CourseDTO;
import com.example.blog.blogdemo.model.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO tocourseDTO(Course course);
    Course toEntity(CourseDTO courseDTO);

    List<CourseDTO> toCourseListDTO(List<Course> courseList);

}
