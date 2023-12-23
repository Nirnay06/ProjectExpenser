package com.expenser.Entity;



import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "file_upload")
@Where(clause = "deleted=0")
@SQLDelete(sql = "update file_upload set deleted=0 where id=?")
@Getter
@Setter
public class FileUpload extends AuditEntity {

	@Id
	@SequenceGenerator(name = "fileUploadSeq",sequenceName = "FILE_UPLOAD_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "fileUploadSeq",strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name = "file_name")
	private String fileName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_identifier", referencedColumnName = "client_identifier")
	private Client client;
	
	@Column(name="file_identifier")
	private String fileIdentifier;
	
	@Lob
	@Column(name ="file_Data" , columnDefinition = "CLOB")
	private String fileData;
	
	@Lob
	@Column(name ="file_meta_data" , columnDefinition = "CLOB")
	private String fileMetaData;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name ="record_start_date")
	private Date recordStartDate;

	@Temporal(TemporalType.DATE)
	@Column(name ="record_end_date")
	private Date recordEndDate;
	
	
	@Column(name = "file_imported")
	private Boolean fileImported=Boolean.FALSE;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="account_identifier", referencedColumnName = "account_identifier")
	private UserAccount account;
	
	@PrePersist
	public void prePersit() {
		this.setFileIdentifier(UUID.randomUUID().toString());
	}
}
