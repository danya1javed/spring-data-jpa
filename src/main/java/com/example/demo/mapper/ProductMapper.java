package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(source = "product", target = "totalPrice", qualifiedByName = "calculateTotalPrice")
  @Mapping(source = "productName", target = "product")
  ProductDto toDto(Product product);

  /**
   * Default Methods
   */
  @Named(value = "calculateTotalPrice")
  default float calculateTotalPrice(Product product){
    return product.getPrice() * product.getQty();
  }
}
