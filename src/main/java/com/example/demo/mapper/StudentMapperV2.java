package com.example.demo.mapper;

import com.example.demo.dto.StudentDtoV2;
import com.example.demo.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StudentMapperV2 {

  @Mapping(source = "student", target = "fullName", qualifiedByName = "getFullName")
  StudentDtoV2 toDTO(Student student);

  @Named("getFullName")
  default String getFullName(Student student){
    return student.getFirstName() + " " + student.getLastName();
  }
}
