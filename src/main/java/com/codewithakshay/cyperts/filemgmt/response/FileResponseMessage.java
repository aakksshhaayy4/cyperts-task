package com.codewithakshay.cyperts.filemgmt.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class FileResponseMessage {

	private String message;

	public FileResponseMessage(String message) {
		super();
		this.message = message;
	}

}
