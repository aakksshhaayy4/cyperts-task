package com.codewithakshay.cyperts.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class FileResponse {

	private String name;
	private String url;
	private String type;
	private long size;
	private Timestamp uploadedAt;

	public FileResponse(String name, String url, String type, long size, Timestamp uploadedAt) {
		super();
		this.name = name;
		this.url = url;
		this.type = type;
		this.size = size;
		this.uploadedAt = uploadedAt;
	}

}
