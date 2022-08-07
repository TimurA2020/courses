package com.example.blog.blogdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    int price;

    double rating;// 1,2,3,4,5 \

    @ManyToOne(fetch = FetchType.LAZY)
    User author;

    @ManyToOne(fetch = FetchType.LAZY)
    CourseCategory courseCategory;
}
