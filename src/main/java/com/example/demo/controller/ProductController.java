package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.Student;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
public class ProductController {

  @Autowired
  ProductService productService;

  @PostMapping
  public Product createProduct(
          @RequestBody Product product
  ){
    return productService.createProduct(product);
  }

  @GetMapping
  public List<Product> getProductList(
          @RequestParam(value = "stud_id", required = false) String stud_id
  ){
    if(stud_id != null){
      return productService.getProductsByStudentId((Long.parseLong(stud_id)));
    } else {
      return productService.getAllProducts();
    }
  }

  @GetMapping("/{pid}")
  public Optional<Product> getProductById(
          @PathVariable(value = "pid") Long pid
  ) throws Exception{
    return productService.getProductById(pid);
  }

  @PutMapping("/{pid}")
  public Product updateStudentById(
          @PathVariable(value = "pid") Long pid,
          @RequestBody Product prod
  ) throws Exception{
    return productService.updateProductById(prod);
  }

  @DeleteMapping("/{pid}")
  public String deleteProductById(
          @PathVariable("pid") Long pid
  ) throws Exception {
    productService.deleteProductById(pid);
    return "Product ID:" + pid + " deleted";
  }

  @GetMapping("/by-first-name/{firstName}")
  public List<Product> getProductsByStudentFirstName(
          @PathVariable("firstName") String firstName
  ){
    return productService.getProductsByStudentFirstName(firstName);
  }

}
