package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface StudentMapper {

    @Mapping(source = "email", target = "emailAddress")
    @Mapping(source = "student", target = "fullName", qualifiedByName = "getFullName")
    @Mapping(source = "student.product", target = "productList")
    StudentDto toDto(Student student);

    @Named(value = "getFullName")
    default public String getFullName(Student student){
        return student.getFirstName() + " " + student.getLastName();
    }
}
