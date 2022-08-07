package com.example.blog.blogdemo.services;

import com.example.blog.blogdemo.DTO.CourseCategoryDTO;
import com.example.blog.blogdemo.DTO.CourseDTO;
import com.example.blog.blogdemo.mappers.CourseCategoryMapper;
import com.example.blog.blogdemo.mappers.CourseMapper;
import com.example.blog.blogdemo.model.Course;
import com.example.blog.blogdemo.model.CourseCategory;
import com.example.blog.blogdemo.repository.CourseCategoryRepository;
import com.example.blog.blogdemo.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseCategoryRepository courseCategoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    public List<CourseCategoryDTO> getAllCategories(){
        return courseCategoryMapper.toCategoryDTOList(courseCategoryRepository.findAll());
    }

    public List<CourseDTO> getAllCourses(){
        return courseMapper.toCourseListDTO(courseRepository.findAll());
    }

    public CourseDTO getCourse(Long id){
        return courseMapper.tocourseDTO(courseRepository.getReferenceById(id));
    }

    public Course addCourse(Course course, Long category_id){
        CourseCategory category = courseCategoryRepository.findById(category_id).orElse(null);
        course.setCourseCategory(category);
        course.setRating(0.0);
        return courseRepository.save(course);
    }

    public CourseCategory addCategory(CourseCategory courseCategory){
        return courseCategoryRepository.save(courseCategory);
    }





}
