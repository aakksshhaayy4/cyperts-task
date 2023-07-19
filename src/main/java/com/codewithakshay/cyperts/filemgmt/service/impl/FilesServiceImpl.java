package com.codewithakshay.cyperts.filemgmt.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.codewithakshay.cyperts.filemgmt.entity.Files;
import com.codewithakshay.cyperts.filemgmt.repository.FilesRepository;
import com.codewithakshay.cyperts.filemgmt.service.FilesService;

@Service
public class FilesServiceImpl implements FilesService {

	@Autowired
	private FilesRepository filesRepository;

	@Override
	public Files saveFile(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Files files = new Files(fileName, file.getContentType(), file.getBytes(), file.getSize());

		return filesRepository.save(files);
	}

	@Override
	public List<Files> getAllFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Files getFileById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
