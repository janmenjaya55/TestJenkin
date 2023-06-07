package com.velocis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity(name = "MediaMaster")
@Table(name = "TM_MEDIA_MASTER")
public class MediaMaster implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "MEDIA_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mediaId;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FILE_FORMAT")
	private String fileFormat;

	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

}
