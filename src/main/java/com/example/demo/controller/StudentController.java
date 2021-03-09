package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  StudentService studentService;

  @GetMapping("/exceptionChecking")
  public String exceptionChecking(@RequestParam(value = "code", required = false) String code){
    switch (code) {
      case "404":
        throw new NotFoundException("Requested resource is not found.");
      case "400":
        throw new BadRequestException("Please provide resource id.");
      case "409":
        throw new AlreadyExistsException("Resource already exists in DB.");
      default:
        return "Yeah...No Exception.";
    }
  }

  @PostMapping
  public String createStudent(
          @RequestBody Student stud
  ) throws Exception {
      String student = studentService.createStudent(stud).toString();
      return student;
  }

  @GetMapping
  public List<Student> getAllStudents(){
      return studentService.getAllStudents();
  }

  @GetMapping("/{sid}")
  public Optional<Student> getStudentById(
          @PathVariable(value = "sid") Long sid
  ) throws Exception{
    return studentService.getStudentById(sid);
  }

  @PutMapping("/{sid}")
  public Student updateStudentById(
          @PathVariable(value = "sid") Long sid,
          @RequestBody Student stud
  ) throws Exception{
    System.out.println(stud);
    return studentService.updateStudentById(stud);
  }

  @DeleteMapping("/{sid}")
  public String deleteStudentById(
    @PathVariable("sid") Long sid
  ) throws Exception {
    studentService.deleteStudentById(sid);
    return "Student ID:" + sid + " deleted";
  }
}
