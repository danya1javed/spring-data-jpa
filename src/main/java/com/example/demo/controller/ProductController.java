package com.example.demo.controller;

import com.example.demo.common.APIResponse;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.Student;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
public class ProductController {

  @Autowired
  ProductService productService;

  @Autowired
  ProductMapper productMapper;

  @Autowired
  APIResponse apiResponse;

  @PostMapping
  public APIResponse createProduct(
          @RequestBody Product product
  ){
   apiResponse.setStatus(HttpStatus.CREATED.value());
   apiResponse.setData(productMapper.toDto(productService.createProduct(product)));
   return apiResponse;
  }

  @GetMapping
  public APIResponse getProductList(
          @RequestParam(value = "stud_id", required = false) String stud_id
  ){
    if(stud_id != null){
      List<ProductDto> productDtoList = new ArrayList<>();
      productService.getProductsByStudentId(Long.parseLong(stud_id)).forEach(prd -> {
        productDtoList.add(productMapper.toDto(prd));
      });
      apiResponse.setStatus(HttpStatus.OK.value());
      apiResponse.setData(productDtoList);
      return apiResponse;
    } else {
      List<ProductDto> productDtoList = new ArrayList<>();
      productService.getAllProducts().forEach(prd -> {
        productDtoList.add(productMapper.toDto(prd));
      });
      apiResponse.setStatus(HttpStatus.OK.value());
      apiResponse.setData(productDtoList);
      return apiResponse;
    }
  }

  @GetMapping("/{pid}")
  public APIResponse getProductById(
          @PathVariable(value = "pid") Long pid
  ) throws Exception{
    apiResponse.setData(productMapper.toDto(productService.getProductById(pid).get()));
    apiResponse.setStatus(HttpStatus.OK.value());
    return apiResponse;
  }

  @PutMapping("/{pid}")
  public APIResponse updateStudentById(
          @PathVariable(value = "pid") Long pid,
          @RequestBody Product prod
  ) throws Exception{
    apiResponse.setData(productMapper.toDto(productService.updateProductById(prod)));
    apiResponse.setStatus(HttpStatus.OK.value());
    return apiResponse;
  }

  @DeleteMapping("/{pid}")
  public APIResponse deleteProductById(
          @PathVariable("pid") Long pid
  ) throws Exception {
    productService.deleteProductById(pid);
    APIResponse apiResponse = new APIResponse();
    apiResponse.setStatus(HttpStatus.OK.value());
    apiResponse.setData(null);
    return apiResponse;
  }

  @GetMapping("/by-first-name/{firstName}")
  public APIResponse getProductsByStudentFirstName(
          @PathVariable("firstName") String firstName
  ){
    List<ProductDto> productDtoList = new ArrayList<>();
    productService.getProductsByStudentFirstName(firstName).forEach(prd -> {
      productDtoList.add(productMapper.toDto(prd));
    });
    apiResponse.setData(productDtoList);
    apiResponse.setStatus(HttpStatus.OK.value());
    return apiResponse;
  }

}
