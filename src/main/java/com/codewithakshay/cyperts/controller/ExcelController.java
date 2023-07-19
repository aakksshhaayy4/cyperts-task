package com.codewithakshay.cyperts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithakshay.cyperts.entity.Employees;
import com.codewithakshay.cyperts.response.FileResponseMessage;
import com.codewithakshay.cyperts.service.EmployeeService;
import com.codewithakshay.cyperts.util.ExcelHelper;

@RestController
@RequestMapping("/excel")
public class ExcelController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/export")
	public ResponseEntity<FileResponseMessage> uploadToExport(@RequestParam("file") MultipartFile file) {
		if (ExcelHelper.isItExcelFile(file)) {
			try {
				employeeService.exportToTable(file);
				return ResponseEntity.status(HttpStatus.OK).body(new FileResponseMessage(
						"File '" + file.getOriginalFilename() + "' Exported to Table Successfully !"));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new FileResponseMessage(
						"File '" + file.getOriginalFilename() + "' Unable to Export to Table !"));
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(new FileResponseMessage("Please Upload File"));
	}

	@GetMapping("/list")
	public ResponseEntity<List<Employees>> getAllEmployees() {
		try {
			List<Employees> employees = employeeService.getAllEmployees();

			if (employees.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
