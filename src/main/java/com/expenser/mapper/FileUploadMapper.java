package com.expenser.mapper;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.expenser.Entity.FileUpload;
import com.expenser.model.FileMetaDataDTO;
import com.expenser.model.FileUploadDTO;
import com.expenser.util.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FileUploadMapper  extends ConfigurableMapper{
	
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	ObjectMapper objectMapper;

	private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

	class FileCustomMapper extends CustomMapper<FileUpload, FileUploadDTO> {

	    @Override
		public void mapAtoB(FileUpload file, FileUploadDTO fileDTO, MappingContext context) {
			try {
				fileDTO.setDataList( objectMapper.readValue(file.getFileData(), new TypeReference<List<List<String>>>(){}));
				fileDTO.setFileMetaData(objectMapper.readValue(file.getFileMetaData(), new TypeReference<FileMetaDataDTO>(){}));
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	    @Override
	    public void mapBtoA(FileUploadDTO fileDTO,FileUpload file, MappingContext context) {
	    	try {
				file.setFileData(objectMapper.writeValueAsString(fileDTO.getDataList()));
				file.setFileMetaData(objectMapper.writeValueAsString(fileDTO.getFileMetaData()));
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    }
	};
	
	FileUploadMapper(){
		mapperFactory.getConverterFactory().registerConverter(new LongToBigDecimalConverter());
		configure();
	}
		public void configure() {
			mapperFactory.classMap(FileUpload.class, FileUploadDTO.class)
			.byDefault()
			.field("account.identifier", "accountIdentifier")
			.mapNulls(false)
			.customize(new FileCustomMapper())
			.register();
		}

    public FileUploadDTO toDto(FileUpload file) {
        return mapperFactory.getMapperFacade(FileUpload.class, FileUploadDTO.class).map(file);
    }

    public FileUpload toEntity(FileUploadDTO fileDTO) {
        return mapperFactory.getMapperFacade(FileUploadDTO.class, FileUpload.class).map(fileDTO);
    }
    
    
    public List<FileUploadDTO> toDtoList(List<FileUpload> files) {
    	List<FileUploadDTO> fileDTOs = new ArrayList<FileUploadDTO>();
    	if(!CollectionUtils.isEmpty(files)) {
    		files.forEach(r -> {
    			fileDTOs.add(toDto(r));
    		});    		
    	}
    	return fileDTOs;
    }
}
