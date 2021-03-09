package com.example.demo.resource;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Student;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public class StudentResource extends RepresentationModel<Student> {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;

  public StudentResource(Student student) {
    this.id = student.getId();
    this.firstName = student.getFirstName();
    this.lastName = student.getLastName();
    this.email = student.getEmail();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
