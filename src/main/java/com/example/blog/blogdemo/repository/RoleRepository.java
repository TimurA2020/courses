package com.example.blog.blogdemo.repository;

import com.example.blog.blogdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String Role);
}
