package com.codewithakshay.cyperts.filemgmt.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.codewithakshay.cyperts.filemgmt.entity.Files;

public interface FilesService {

	public Files saveFile(MultipartFile file) throws IOException;

	public List<Files> getAllFiles();

	public Files getFileById(String id);

}
