package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Product")
@Table(
        name = "product"
//        uniqueConstraints = {
//                @UniqueConstraint(name = "product_productName_unique", columnNames = "product_name")
//        }
)  // name as in db usually lowercase
public class Product {
  @Id
  @SequenceGenerator(
          name = "product_sequence",
          sequenceName = "product_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          generator = "product_sequence",
          strategy = GenerationType.SEQUENCE
  )
  private Long id;
  @Column(
          name = "product_name",
          nullable = false,
          columnDefinition = "TEXT"
  )
  private String productName;
  @Column(
          name = "qty",
          nullable = false
  )
  private int qty;
  @Column(
          name = "price",
          nullable = false,
          columnDefinition = "FLOAT"
  )
  private float price;
  @ManyToOne(fetch = FetchType.EAGER) // if set: LAZY -> later on you have to manually fetch it.
 //  @JoinColumn is used to fine define column name, type etc.
  private Student student;

  public Product(){}

  public Product(String productName, int qty, float price) {
    this.productName = productName;
    this.qty = qty;
    this.price = price;
  }

  @Override
  public String toString() {
    return "Product{" +
            "id=" + id +
            ", productName='" + productName + '\'' +
            ", qty=" + qty +
            ", price=" + price +
            '}';
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public int getQty() {
    return qty;
  }

  public void setQty(int qty) {
    this.qty = qty;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

}
