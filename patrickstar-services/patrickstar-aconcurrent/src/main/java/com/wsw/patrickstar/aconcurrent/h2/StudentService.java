package com.wsw.patrickstar.aconcurrent.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/18 11:06
 */
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void insertStudent(List<Student> students) {
        studentRepository.saveAll(students);
    }

    public List<Student> getStudents(Integer age) {
        return studentRepository.findAllByAge(age);
    }
}
