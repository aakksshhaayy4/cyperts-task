package com.codewithakshay.cyperts.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.codewithakshay.cyperts.entity.Files;

public interface FilesService {

	public Files saveFile(MultipartFile file) throws IOException;

	public Stream<Files> getAllFiles();

	public Files getFileById(String id);

}
