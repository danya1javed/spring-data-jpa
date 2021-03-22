package com.example.demo;

import com.example.demo.entity.Product;
import com.example.demo.entity.Student;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//            StudentRepository studentRepository,
//            ProductRepository productRepository
//    ) {
//      return args -> {
//        Student student = new Student(
//                "Tom",
//                "Hardy",
//                "tom.hardy@mail.com",
//                45
//        );
//        studentRepository.save(student);
//        Product product1 = new Product(
//                "mouse",
//                1,
//                2500.0f);
//        product1.setStudent(student);
//        productRepository.save(product1);
//        Product product2 = new Product(
//                "keyboard",
//                1,
//                9500.0f);
//        product2.setStudent(student);
//        productRepository.save(product2);
//
//        System.out.println(productRepository.findById(Integer.toUnsignedLong(1)));
//      };
//    }
}
