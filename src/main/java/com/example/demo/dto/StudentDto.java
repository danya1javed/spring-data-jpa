package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
  private String emailAddress;
  private int age;
  private String fullName;
  private List<ProductDto> productList;

}
