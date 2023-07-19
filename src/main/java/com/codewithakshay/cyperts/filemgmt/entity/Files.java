package com.codewithakshay.cyperts.filemgmt.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "cyperts", name = "files")
@Data
@NoArgsConstructor
public class Files {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String name;
	private String type;
	@Lob
	private byte[] data;
	private long size;
	@Transient
	private String url;
	@UpdateTimestamp
	private Timestamp uploadedAt;

	public Files(String name, String type, byte[] data, long size) {
		super();
		this.name = name;
		this.type = type;
		this.data = data;
		this.size = size;
	}

	public Files(String name, String type, long size, String url) {
		super();
		this.name = name;
		this.type = type;
		this.size = size;
		this.url = url;
	}

}
