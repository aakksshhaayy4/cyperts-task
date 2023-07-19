package com.codewithakshay.cyperts.filemgmt.excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithakshay.cyperts.filemgmt.excel.entity.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {

}
