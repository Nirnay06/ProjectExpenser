package com.expenser.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="product_configurations")
@Where(clause = "deleted != 1 ")
@SQLDelete(sql = "update product_configurations set deleted = 1 where id = ?")
public class ProductConfiguration extends AuditEntity{

	@Id
	@SequenceGenerator(name = "ProductConfigSeq", allocationSize = 1 , sequenceName = "PRODUCT_CONFIG_SEQ")
	@GeneratedValue(generator = "ProductConfigSeq")
	private Long id;
	
	private String key;
	
	private String value;
	private String uuid;
}
