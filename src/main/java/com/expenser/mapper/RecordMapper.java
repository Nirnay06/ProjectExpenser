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

import com.expenser.Entity.UserRecord;
import com.expenser.model.RecordDTO;
import com.expenser.util.DateUtil;

@Component
public class RecordMapper  extends ConfigurableMapper{
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	LabelMapper labelMapper;

	private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

	class RecordCustomMapper extends CustomMapper<UserRecord, RecordDTO> {

	    @Override
		public void mapAtoB(UserRecord record, RecordDTO recordDTO, MappingContext context) {
			recordDTO.setRecordDate(DateUtil.getDateString(record.getDate()));
			recordDTO.setRecordTime(DateUtil.getTimeString(record.getDate()));
			recordDTO.setRecordCategory(categoryMapper.toDto(record.getCategory()));
			recordDTO
					.setLabels(record.getLabels().stream().map(r -> labelMapper.toDto(r)).collect(Collectors.toList()));
			recordDTO.setUserLabelIdentifiers(record.getLabels().stream().map(l -> l.getUserLabel().getIdentifier())
					.collect(Collectors.toList()));
		}

	    @Override
	    public void mapBtoA(RecordDTO recordDTO,UserRecord record, MappingContext context) {
			Date recordDate = null;
			try {
				recordDate = DateUtil.getDateFromDateAndTimeString(recordDTO.getRecordDate(),
						recordDTO.getRecordTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			record.setDate(recordDate);
	    }
	};
	
	RecordMapper(){
		mapperFactory.getConverterFactory().registerConverter(new LongToBigDecimalConverter());
		configure();
	}
		public void configure() {
		   System.out.println("nirnay");
			mapperFactory.classMap(UserRecord.class, RecordDTO.class)
			.byDefault()
			.field("category.identifier", "categoryIdentifier")
			.field("account.identifier", "accountIdentifier")
			.field("currency.identifier", "currencyIdentifier")
			.mapNulls(false)
			.customize(new RecordCustomMapper()).register();
		}

    public RecordDTO toDto(UserRecord record) {
        return mapperFactory.getMapperFacade(UserRecord.class, RecordDTO.class).map(record);
    }

    public UserRecord toEntity(RecordDTO recordDTO) {
        return mapperFactory.getMapperFacade(RecordDTO.class, UserRecord.class).map(recordDTO);
    }
    
    public UserRecord updateEntityFromDTO(RecordDTO dto, UserRecord entity) {
    	MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        mapperFacade.map(dto, entity);
        return entity;
    }
    
    public List<RecordDTO> toDtoList(List<UserRecord> records) {
    	List<RecordDTO> recordDTOs = new ArrayList<RecordDTO>();
    	if(!CollectionUtils.isEmpty(records)) {
    		records.forEach(r -> {
    			recordDTOs.add(toDto(r));
    		});    		
    	}
    	return recordDTOs;
    }
}
