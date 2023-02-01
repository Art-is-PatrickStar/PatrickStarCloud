package com.wsw.patrickstar.aconcurrent.h2;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/18 11:10
 */

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/insert")
    public void insertStudent(@RequestBody Student student) {
        List<Student> students = new ArrayList<>();
        students.add(student);
        studentService.insertStudent(students);
    }

    @GetMapping("/get")
    public String getStudents() {
         return JSON.toJSONString(studentService.getStudents(1));
    }
}
