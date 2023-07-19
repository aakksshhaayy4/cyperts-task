package com.codewithakshay.cyperts.filemgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithakshay.cyperts.filemgmt.entity.Files;

@Repository
public interface FilesRepository extends JpaRepository<Files, String> {

}
