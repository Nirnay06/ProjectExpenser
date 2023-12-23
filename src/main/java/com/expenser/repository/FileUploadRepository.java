package com.expenser.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.expenser.Entity.FileUpload;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {

	@Query("select f from FileUpload f where f.fileidentifier = :fileIdentifier")
	FileUpload findByFileIdentifier(String fileIdentifier);

	
}
