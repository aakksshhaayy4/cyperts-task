package com.codewithakshay.cyperts.filemgmt.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(schema = "cyperts", name = "files")
@Data
public class Files {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String name;
	private String type;
	private byte[] data;
	private long size;
	@UpdateTimestamp
	private Timestamp uploadedAt;

	public Files(String name, String type, byte[] data, long size) {
		super();
		this.name = name;
		this.type = type;
		this.data = data;
		this.size = size;
	}

}
