package com.example.demo.controller;

import com.example.demo.common.APIResponse;
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  StudentService studentService;

  @Autowired
  StudentMapper studentMapper;

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
  public APIResponse createStudent(
          @Valid @RequestBody Student stud
  ) throws Exception {
      APIResponse apiResponse = new APIResponse();
      apiResponse.setData(studentMapper.toDto(studentService.createStudent(stud)));
      apiResponse.setStatus(HttpStatus.CREATED.value());
      return apiResponse;
  }

  @GetMapping(produces = { "application/xml"})
  public APIResponse getAllStudents(){
    APIResponse apiResponse = new APIResponse();
    List<StudentDto> studentDtos = new ArrayList<>();
    studentService.getAllStudents().forEach(stud -> {
      studentDtos.add(studentMapper.toDto(stud));
    });
    apiResponse.setData(studentDtos);
    apiResponse.setStatus(HttpStatus.OK.value());
    return apiResponse;
  }

  @GetMapping("/{sid}")
  public APIResponse getStudentById(
          @PathVariable(value = "sid") Long sid
  ) throws Exception{
      APIResponse apiResponse = new APIResponse();
      Optional<Student> student = studentService.getStudentById(sid);
      if(student.isPresent()){
        StudentDto studentDto = studentMapper.toDto(student.get());
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(studentDto);
      }
      apiResponse.setStatus(HttpStatus.NO_CONTENT.value());
      return apiResponse;
  }

  @PutMapping("/{sid}")
  public APIResponse updateStudentById(
          @PathVariable(value = "sid") Long sid,
          @RequestBody Student stud
  ) throws Exception{
    APIResponse apiResponse = new APIResponse();
    apiResponse.setData(studentMapper.toDto(studentService.updateStudentById(stud)));
    apiResponse.setStatus(HttpStatus.OK.value());
    return apiResponse;
  }

  @DeleteMapping("/{sid}")
  public APIResponse deleteStudentById(
    @PathVariable("sid") Long sid
  ) throws Exception {
    studentService.deleteStudentById(sid);
    APIResponse apiResponse = new APIResponse();
    apiResponse.setStatus(HttpStatus.OK.value());
    apiResponse.setData(null);
    return apiResponse;
  }
}
