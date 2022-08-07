package com.example.blog.blogdemo.api;

import com.example.blog.blogdemo.DTO.CourseCategoryDTO;
import com.example.blog.blogdemo.DTO.CourseDTO;
import com.example.blog.blogdemo.model.Course;
import com.example.blog.blogdemo.model.CourseCategory;
import com.example.blog.blogdemo.model.User;
import com.example.blog.blogdemo.services.CourseService;
import com.example.blog.blogdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;


    @GetMapping("/")
    public String indexPage(Model model){
        User user = getCurrentUser();
        model.addAttribute("user", user);
        return "/index";
    }

    @GetMapping("/enter")
    public String enterPage(Model model){
        return "login";
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminPage(Model model){
        return "admin";
    }

    @GetMapping("/forbidden")
    public String forbiddenPage(){
        return "forbidden";
    }



    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User user = (User) authentication.getPrincipal();
            return user;
        }else{
            return null;
        }
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }


    @PostMapping("/registeruser")
    public String registerUser(@RequestParam(name = "user_email") String email,
                           @RequestParam(name = "user_password") String password,
                           @RequestParam(name = "user_fullName") String fullname,
                           @RequestParam(name = "user_rePassword") String repassword){
        if(password.equals(repassword)){
             User user = new User();
             user.setEmail(email);
             user.setPassword(password);
             user.setFullName(fullname);
             user = userService.registerUser(user);
             if(user != null){
                 return "redirect:/register?success";
             }
        }
        return "redirect:/register?error";

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/addcourse")
    public String addCourse(Model model){
        List<CourseCategoryDTO> courseCategoryList = courseService.getAllCategories();
        model.addAttribute("coursecategorylist", courseCategoryList);
        return "addcourse";
    }

    @PostMapping(value = "/addcourse")
    public String addCourse(@RequestParam(name = "course_title") String title,
                            @RequestParam(name = "course_content") String content,
                            @RequestParam(name = "course_price") int price,
                            @RequestParam(name = "course_category") Long course_category){
        Course course = new Course();
        course.setName(title);
        course.setDescription(content);
        course.setPrice(price);
        course.setAuthor(getCurrentUser());
        courseService.addCourse(course, course_category);
        return "redirect:/";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/addcategory")
    public String addCourseCategory(){
        return "addcoursecategory";
    }

    @PostMapping(value = "/addcategory")
    public String addCourseCategory(@RequestParam(name = "name") String name){
        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setName(name);
        courseService.addCategory(courseCategory);
        return "redirect:/";
    }

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping(value = "/updatepassword")
//    public String updatePassword(@RequestParam(name = "old_password") String oldPassword,
//                                 @RequestParam(name = "new_password") String newPassword,
//                                 @RequestParam(name = "re_new_password") String reNewPassword){
//        if(newPassword.equals(reNewPassword)){
//            User updatedUser = userService.updatePassword(getCurrentUser() ,oldPassword, newPassword);
//            if(updatedUser != null){
//                return "redirect:/profile?success";
//            }
//            return "redirect:/profile?error";
//        }
//        return "redirect:/profile?error";
//    }

    @GetMapping(value = "/details/{id}")
    public String getDetails(){
        return "details";
    }




}
