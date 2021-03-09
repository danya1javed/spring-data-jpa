package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Student") // best practice to specify name
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames = "email")
        }
)
public class Student extends RepresentationModel<Student> {

//  Adding strong validation will probably fail partial use of the Student Object.

  @Id
  @SequenceGenerator(
          name = "student_sequence",
          sequenceName = "student_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "student_sequence"
  )
  @Column(
          name = "id",
          updatable = false
  )
  private Long id;
  @Column( // represents DB validations
          name = "first_name",
          nullable = false,
          columnDefinition = "TEXT"
  )
//  @NotEmpty(message = "First name should have atleast 2 characters")
  private String firstName;
  @Column(
          name = "last_name",
          nullable = false,
          columnDefinition = "TEXT"
  )
  private String lastName;
  @Column(
          name = "email",
          nullable = false,
          columnDefinition = "TEXT"
  )
  private String email;
  @Column(
          name = "age",
          nullable = false
  )
  private int age;

//  @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
//  @JoinColumn(name = "student_product", referencedColumnName = "id")
  @OneToMany(mappedBy = "student")
  @JsonIgnore
  private List<Product> product = new ArrayList<Product>();

  public Student(){}

  public Student(String firstName, String lastName, String email, int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public List<Product> getProduct() {
    return product;
  }

  public void setProduct(List<Product> product) {
    this.product = product;
  }

  @Override
  public String toString() {
    return "Student{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", age=" + age +
            '}';
  }
}
