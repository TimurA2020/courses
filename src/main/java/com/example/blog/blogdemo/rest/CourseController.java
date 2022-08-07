package com.example.blog.blogdemo.rest;

import com.example.blog.blogdemo.DTO.CourseDTO;
import com.example.blog.blogdemo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/courses")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable (name = "id") Long id){
        return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
    }
}
