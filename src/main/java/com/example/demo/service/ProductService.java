package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.Student;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

//  CREATE PRODUCT
  public Product createProduct(Product prd) {
    return productRepository.save(prd);
  }

//  GET ALL
  public List<Product> getAllProducts(){
    return productRepository.findAll();
  }

//  GET PRODUCT BY STUDENT ID
  public List<Product> getProductsByStudentId(Long id){
    return productRepository.findByStudentId(id);
  }

// GET BY ID
//  use optional type if not sure what to return.
  public Optional<Product> getProductById(Long id) throws NotFoundException {
    Optional<Product> stud = productRepository.findById(id);
    if(!stud.isPresent()){
      throw new NotFoundException("Product not found - ID:" + id);
    }
    return stud;
  }

  //  UPDATE BY ID
  public Product updateProductById(Product product) throws NotFoundException {
    Optional<Product> existingProduct = productRepository.findById(product.getId());
    if(!existingProduct.isPresent()){
      throw new NotFoundException("Product not found - ID:"+ product.getId());
    }
    // Only price and quantity are updatable
    if(product.getQty() != 0){
      existingProduct.get().setQty(product.getQty());
    } else if(product.getPrice() != 0){
      existingProduct.get().setPrice(product.getPrice());
    }
    return productRepository.save(existingProduct.get());
  }

  //  DELETE BY ID
  public void deleteProductById(Long id) throws NotFoundException {
    Optional<Product> existingProduct = productRepository.findById(id);
    if(!existingProduct.isPresent()){
      throw new NotFoundException("Product not found - ID:" + id);
    }
    productRepository.deleteById(id);
  }

}
