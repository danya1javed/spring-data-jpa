package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

  @Autowired
  StudentRepository studentRepository;

//  CRUD

//  CREATE
  public Student createStudent(Student student) throws AlreadyExistsException {
    Student exists = studentRepository.findByEmail(student.getEmail());
    if (exists != null) {
      throw new AlreadyExistsException("Email:"+ student.getEmail() + " already exists.");
    }
    return studentRepository.save(student);
  }

//  GET ALL
  public List<Student> getAllStudents(){
    return studentRepository.findAll();
  }

// GET BY ID
//  use optional type if not sure what to return.
  @Transactional
  public Optional<Student> getStudentById(Long id) throws NotFoundException {
    Optional<Student> stud = studentRepository.findById(id);
    if(!stud.isPresent()){
      throw new NotFoundException("Student not found.");
    }
    return stud;
  }

//  UPDATE BY ID
  public Student updateStudentById(Student student) throws NotFoundException {
    Optional<Student> existingStudent = studentRepository.findById(student.getId());
    if(!existingStudent.isPresent()){
      throw new NotFoundException("Student not found - ID:" + student.getId());
    }
    if(student.getFirstName() != null){
      existingStudent.get().setFirstName(student.getFirstName());
    } else if(student.getLastName() != null){
      existingStudent.get().setLastName(student.getLastName());
    }
    return studentRepository.save(existingStudent.get());
  }

  //  DELETE BY ID
  public void deleteStudentById(Long id) throws NotFoundException {
    Optional<Student> existingStudent = studentRepository.findById(id);
    if(!existingStudent.isPresent()){
      throw new NotFoundException("Student not found - ID:" + id);
    }
    studentRepository.deleteById(id);
  }
}
