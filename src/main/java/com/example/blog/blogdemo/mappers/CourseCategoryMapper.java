package com.example.blog.blogdemo.mappers;

import com.example.blog.blogdemo.DTO.CourseCategoryDTO;
import com.example.blog.blogdemo.model.CourseCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseCategoryMapper {

    CourseCategoryDTO toCourseCategoryDTO(CourseCategory courseCategory);
    CourseCategory toCourseCategoryEntity(CourseCategoryDTO courseCategoryDTO);

    List<CourseCategoryDTO> toCategoryDTOList(List<CourseCategory> CourseCategoryList);



}
