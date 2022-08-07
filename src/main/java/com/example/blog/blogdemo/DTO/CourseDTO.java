package com.example.blog.blogdemo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private int price;
    private double rating;
    private CourseCategoryDTO courseCategory;
}
