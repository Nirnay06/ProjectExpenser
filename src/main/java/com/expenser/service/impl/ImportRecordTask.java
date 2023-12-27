package com.expenser.service.impl;

import java.util.concurrent.RecursiveTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenser.model.FileMetaDataDTO;
import com.expenser.model.FileUploadDTO;
import com.expenser.service.RecordService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportRecordTask extends RecursiveTask<Integer> {
	
	private Integer recordStartIndex;
	private Integer recordEndIndex;
	private FileUploadDTO fileUploadDTO;
	
	private RecordService recordService;

 
	@Override
	protected Integer compute() {
		if(recordEndIndex-recordStartIndex > 50) {
//			ImportRecordTask task1 = createTask(recordStartIndex, (recordStartIndex + recordEndIndex)/2, fileUploadDTO);
//			ImportRecordTask task2 = createTask((recordStartIndex + recordEndIndex)/2+1, recordEndIndex, fileUploadDTO);
//			Integer i=0;
//			i+= task1.join();
//			i+=task2.join();
			 int mid = recordStartIndex + (recordEndIndex - recordStartIndex) / 2;
			 ImportRecordTask leftTask = new ImportRecordTask(recordStartIndex, mid, fileUploadDTO, recordService);
			 ImportRecordTask rightTask = new ImportRecordTask(mid + 1, recordEndIndex, fileUploadDTO, recordService);

	            // Fork the subtasks
	           int i = 0;
	           invokeAll(leftTask, rightTask);
			return i;
		}else {
			Integer i = recordService.importRecordFromFile(recordStartIndex, recordEndIndex, fileUploadDTO);
			return i;
		}
	}

	public ImportRecordTask() {
		
	}
	
	
	  public ImportRecordTask(Integer recordStartIndex, Integer recordEndIndex, FileUploadDTO fileUploadDTO, RecordService recordService) {
	        this.recordStartIndex = recordStartIndex;
	        this.recordEndIndex = recordEndIndex;
	        this.fileUploadDTO = fileUploadDTO;
	        this.recordService= recordService;
	    }
	
	public ImportRecordTask createTask(Integer recordStartIndex, Integer recordEndIndex, FileUploadDTO fileUploadDTO) {

		this.recordStartIndex = recordStartIndex;
		this.recordEndIndex = recordEndIndex;
		this.fileUploadDTO = fileUploadDTO;
		return this;
    }
}
