package com.expenser.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.expenser.exception.BusinessException;
import com.expenser.model.ClientDTO;
import com.expenser.model.FileMetaDataDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.jsonwebtoken.io.IOException;


@Service
public interface FileUploadService {

	public Map<String, Object> readExcel(InputStream inputStream, String string)
			throws IOException, EncryptedDocumentException, java.io.IOException, BusinessException;

	void validateFileSize(MultipartFile file) throws BusinessException;

	public void storeFileData(String originalFilename, Map<String, Object> data, ClientDTO client, String accountIdentifier) throws JsonProcessingException;

	public void importRecordsFromFile(String fileIdentifier, FileMetaDataDTO metadata) throws BusinessException;
}
