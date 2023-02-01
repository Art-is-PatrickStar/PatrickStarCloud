package com.wsw.patrickstar.aconcurrent.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/11/18 11:03
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByAge(Integer age);
}
