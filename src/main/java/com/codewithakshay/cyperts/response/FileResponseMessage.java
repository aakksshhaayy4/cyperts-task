package com.codewithakshay.cyperts.response;

import lombok.Data;

@Data
public class FileResponseMessage {

	private String message;

	public FileResponseMessage(String message) {
		super();
		this.message = message;
	}

}
