package com.expenser.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.expenser.exception.BusinessException;
import com.expenser.model.APIResponseDTO;
import com.expenser.model.ClientDTO;
import com.expenser.model.FileMetaDataDTO;
import com.expenser.service.FileUploadService;
import com.expenser.util.SecurityUtils;

@RestController
@RequestMapping("/files")
public class FileUploadController {

	@Autowired
	FileUploadService fileUploadService;
	
	@PostMapping("/uploadRecordFiles")
	public Map<String, Object> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("accountIdentifier") String accountIdentifier) throws BusinessException {
		try {
			ClientDTO client = SecurityUtils.getClientFromSession();
			fileUploadService.validateFileSize(file);
			Map<String, Object> data =  fileUploadService.readExcel(file.getInputStream(), file.getOriginalFilename());
			fileUploadService.storeFileData(file.getOriginalFilename(), data, client, accountIdentifier);
			return data;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/importRecords/{fileIdentifier}")
	public ResponseEntity<?> importRecordsFromFile(@PathVariable("fileIdentifier") String fileIdentifier, @RequestBody FileMetaDataDTO metadata) throws BusinessException {
		try {
			ClientDTO client = SecurityUtils.getClientFromSession();
			fileUploadService.importRecordsFromFile(fileIdentifier, metadata);
			return new ResponseEntity<APIResponseDTO>(new APIResponseDTO("File uploaded successfully.", true), HttpStatus.OK);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
