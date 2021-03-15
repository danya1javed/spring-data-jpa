package com.example.demo.controller;

import com.example.demo.common.APIResponse;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.StudentMapperV2;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("versioning/query-param")
public class VersioningUsingQueryParam {

  @Autowired
  StudentService studentService;

  @Autowired
  APIResponse apiResponse;

  @Autowired
  StudentMapper studentMapper;

  @Autowired
  StudentMapperV2 studentMapperV2;

  @GetMapping(
          path = "/{sid}",
          params = "version=1"
  )
  public APIResponse version1(
          @PathVariable("sid") @Min(1) Long sid
  ){
    Student student = studentService.getStudentById(sid).get();
    apiResponse.setData(studentMapper.toDto(student));
    apiResponse.setStatus(HttpStatus.OK.value());
    return apiResponse;
  }

  @GetMapping(
          path = "/{sid}",
          params = "version=2"
  )
  public APIResponse version2(
          @PathVariable("sid") @Min(1) Long sid
  ){
    Student student = studentService.getStudentById(sid).get();
    apiResponse.setData(studentMapperV2.toDTO(student));
    apiResponse.setStatus(HttpStatus.OK.value());
    return apiResponse;
  }
}
