package com.expenser.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.expenser.Entity.FileUpload;
import com.expenser.Entity.UserAccount;
import com.expenser.api.AccountService;
import com.expenser.api.ClientService;
import com.expenser.api.FileUploadService;
import com.expenser.api.RecordService;
import com.expenser.exception.BusinessException;
import com.expenser.mapper.FileUploadMapper;
import com.expenser.model.ClientDTO;
import com.expenser.model.FileMetaDataDTO;
import com.expenser.model.FileUploadDTO;
import com.expenser.repository.FileUploadRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	private static final String FILE_DATA = "fileData";
	private static final String FILE_META_DATA = "fileMetaData";
	final List<String> validFileExt = Arrays.asList(".xls",".xlsx");
	final long MAX_FILE_SIZE = 2097152l;
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired 
	FileUploadRepository fileUploadRepository;
	
	@Autowired
	FileUploadMapper fileMapper;
	
//	@Autowired 
//	ImportRecordTask importRecordTask;
	
	@Autowired
	RecordService recordService;
	
	
	@Override
	public Map<String, Object> readExcel(InputStream inputStream, String fileName)
			throws IOException, EncryptedDocumentException, java.io.IOException, BusinessException {
		Map<String, Object> result = new HashMap<>();
		List<List<String>> data = new ArrayList<>();
		validateExcelFileName(fileName);
		int rowCount =0;
		int columnCount = 0;
		List<String> alphabetList = new ArrayList<>();
		List<String> headerList = new ArrayList<>();
		try (Workbook workbook = WorkbookFactory.create(inputStream)) {
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			
			while (rowIterator.hasNext()) {
				rowCount +=1;
					
				Row row = rowIterator.next();
				
				Iterator<Cell> cellIterator = row.cellIterator();

				List<String> rowData = new ArrayList<>();
				int lastCellIndex = -1;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if(cell.toString().trim().length()!=0) {
						if(rowCount==1) {
							alphabetList.add(generateAlphabeticSeries(columnCount));
							columnCount +=1;
							headerList.add(cell.toString());
						}
						if( cell.getColumnIndex() - lastCellIndex > 1) {
							for(int i = 0 ;i<cell.getColumnIndex() - lastCellIndex-1 ; i++ ) {
								rowData.add(null);
							}
						}
						
						lastCellIndex = cell.getColumnIndex();
						rowData.add(cell.toString());
					}
				}
				if (lastCellIndex != -1) {
					if (rowCount != 1 && lastCellIndex < columnCount) {
						for (int i = 0; i < columnCount - lastCellIndex - 1; i++) {
							rowData.add(null);
						}
					}
					data.add(rowData);
				}else {
					rowCount-=1;
				}
			}
		}
		FileMetaDataDTO metaData= new FileMetaDataDTO();
		metaData.setRowCount(rowCount);
		metaData.setColumnCount(columnCount);
		metaData.setAlphabetList(alphabetList);
		metaData.setSelectedLastRow(rowCount);
		result.put(FILE_DATA, data);
		result.put(FILE_META_DATA, metaData);
		return result;
	}

	private void validateExcelFileName(String fileName) throws BusinessException {
		if(fileName.indexOf(".")==-1 && fileName.indexOf(".", fileName.indexOf("."))!=-1) {
			throw new BusinessException("The file name is not valid. Please Enter a valid filename.");
		}
		for(String ext : validFileExt) {
			if(fileName.endsWith(ext)) {
				return;
			}
		}
		throw new BusinessException("The file type is not valid. Only " + validFileExt.toString() + " are supported.");
	}
	
	@Override
	public void validateFileSize(MultipartFile file) throws BusinessException {
		if(file.getSize() > MAX_FILE_SIZE) {
			throw new BusinessException("The file size limitr exceed. Max file size allowed is " + MAX_FILE_SIZE/1024/1024 + "MB. ");
		}
	}
	
	
	private static String generateAlphabeticSeries(int index) {
        StringBuilder result = new StringBuilder();
        int totalChars = 26;

        while (index >= 0) {
            char currentChar = (char) ('A' + (index % totalChars));
            result.insert(0, currentChar);

            index = (index / totalChars) - 1;
        }

        return result.toString();
    }

	@Override
	public void storeFileData(String originalFilename, Map<String, Object> data, ClientDTO client, String accountIdentifier) throws JsonProcessingException {
		FileUpload fileToBeUploaded = new FileUpload();
		fileToBeUploaded.setFileName(originalFilename);
		fileToBeUploaded.setClient(clientService.findByClientIdentifier(client.getClientIdentifier()));
		fileToBeUploaded.setAccount(accountService.findByIdentifier(accountIdentifier));
		fileToBeUploaded.setFileData(objectMapper.writeValueAsString(data.get(FILE_DATA)));
		fileToBeUploaded.setFileMetaData(objectMapper.writeValueAsString(data.get(FILE_META_DATA)));
		
		fileToBeUploaded = fileUploadRepository.save(fileToBeUploaded);
		FileMetaDataDTO metadata = (FileMetaDataDTO) data.get(FILE_META_DATA);
		metadata.setFileIdentifier(fileToBeUploaded.getFileIdentifier());
		metadata.setAccountIdentifier(accountIdentifier);
		data.put(FILE_META_DATA, metadata);
	}

	@Override
	public void importRecordsFromFile(String fileIdentifier, FileMetaDataDTO metadata) throws BusinessException{
		
		UserAccount account = accountService.findByIdentifier(metadata.getAccountIdentifier());
		if(account!=null) {
//			validateFileData(fileIdentifier, metadata);
			FileUpload file = fileUploadRepository.findByFileIdentifier(fileIdentifier);
			FileUploadDTO fileDTO = fileMapper.toDto(file);
			ForkJoinPool pool = new ForkJoinPool();
			ImportRecordTask task = new ImportRecordTask(metadata.getSelectedFirstRow(), metadata.getSelectedLastRow(), fileDTO, recordService);
			pool.invoke(task);
		}else {
			throw new BusinessException("Bank Account not found.");
		}
	}

}
