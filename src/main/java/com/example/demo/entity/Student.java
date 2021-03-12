package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
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
  @NotEmpty(message = "First name should have atleast 2 characters")
  @Size(min = 2, max = 50)
  private String firstName;

  @Column(
          name = "last_name",
          nullable = false,
          columnDefinition = "TEXT"
  )
  @NotEmpty(message = "last name should have atleast 2 characters")
  @Size(min = 2, max = 50)
  private String lastName;

  @Column(
          name = "email",
          nullable = false,
          columnDefinition = "TEXT"
  )
  @NotEmpty(message = "Email required")
  @Size(min = 5, max = 100)
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
}
