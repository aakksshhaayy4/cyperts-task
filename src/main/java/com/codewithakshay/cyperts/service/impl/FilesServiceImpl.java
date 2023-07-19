package com.codewithakshay.cyperts.service.impl;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.codewithakshay.cyperts.entity.Files;
import com.codewithakshay.cyperts.repository.FilesRepository;
import com.codewithakshay.cyperts.service.FilesService;

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
	public Stream<Files> getAllFiles() {

		return filesRepository.findAll().stream();
	}

	@Override
	public Files getFileById(String id) {

		return filesRepository.findById(id).get();
	}

}
