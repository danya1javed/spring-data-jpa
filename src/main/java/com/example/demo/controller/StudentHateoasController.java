package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/hateoas/students")
public class StudentHateoasController {

  @Autowired
  StudentService studentService;

  @GetMapping
  public CollectionModel<Student> getAllStudents(){
    List<Student> studList = studentService.getAllStudents();
    for(Student stud: studList){
      Link selfLink = WebMvcLinkBuilder.linkTo(StudentController.class).slash(stud.getId()).withSelfRel();

      Link productsLink = WebMvcLinkBuilder
              .linkTo(methodOn(ProductController.class)
              .getProductList(stud.getId().toString()))
              .withRel("products");
      System.out.println(selfLink);
      stud.add(selfLink);
      stud.add(productsLink);
    }
    Link selfLinkAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
    CollectionModel<Student> finalEntities = CollectionModel.of(studList, selfLinkAllUsers);

    return finalEntities;
  }

}
