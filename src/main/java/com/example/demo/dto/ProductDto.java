package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductDto {
  private String product;
  private float price;
  private int qty;
  private float totalPrice;
}
