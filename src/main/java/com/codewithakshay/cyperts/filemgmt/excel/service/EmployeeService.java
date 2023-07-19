package com.codewithakshay.cyperts.filemgmt.excel.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.codewithakshay.cyperts.filemgmt.excel.entity.Employees;

public interface EmployeeService {

	public List<Employees> getAllEmployees();

	public void exportToTable(MultipartFile file);

}
