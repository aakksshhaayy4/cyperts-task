package com.codewithakshay.cyperts.filemgmt.excel.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(schema = "cyperts", name = "emp_data")
@Data
public class Employees {

	@Id
	private long id;
	private String name;
	private String city;
	private String dept;
	private int age;

}
