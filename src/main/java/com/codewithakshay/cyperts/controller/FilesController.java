package com.codewithakshay.cyperts.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.codewithakshay.cyperts.entity.Files;
import com.codewithakshay.cyperts.response.FileResponse;
import com.codewithakshay.cyperts.response.FileResponseMessage;
import com.codewithakshay.cyperts.service.FilesService;

@RestController
@RequestMapping("/files")
public class FilesController {

	@Autowired
	private FilesService filesService;

	@PostMapping("/upload")
	public ResponseEntity<FileResponseMessage> saveFile(@RequestParam("file") MultipartFile file) {
		try {
			Files savedFile = filesService.saveFile(file);
			if (savedFile != null)
				return ResponseEntity.status(HttpStatus.OK).body(
						new FileResponseMessage("File '" + file.getOriginalFilename() + "' Uploaded Successfully "));
			else
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(new FileResponseMessage("File '" + file.getOriginalFilename() + "' Not Uploaded "));
		} catch (Exception e) {
			System.out.println("exception : " + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new FileResponseMessage(
					"Exception while saving  '" + file.getOriginalFilename() + "'\n Exception is : " + e));
		}
	}

	@GetMapping("/allfiles")
	public ResponseEntity<Object> getAllFiles() {
		try {
			List<FileResponse> allFiles = filesService.getAllFiles().map(file -> {
				String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/")
						.path(file.getId()).toUriString();

				return new FileResponse(file.getName(), fileUrl, file.getType(), file.getSize(), file.getUploadedAt());
			}).collect(Collectors.toList());

			if (!allFiles.isEmpty())
				return new ResponseEntity<>(allFiles, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> getFileById(@PathVariable String id) {
		try {
			Files fileById = filesService.getFileById(id);

			if (fileById != null)
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileById.getName() + "\"")
						.body(fileById.getData());
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
