package com.tekusource.superfly.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="image")
public class Image implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6825533996826264688L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="fileName")
	private String fileName;
	
	@Column(name="fileDescription")
	private String fileDescription;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userDetail", insertable=true, updatable=true, nullable=false)
	private UserDetail userDetail;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="album", referencedColumnName="id")
	private Album album;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileDescription() {
		return fileDescription;
	}
	
	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
	
	public Album getAlbum() {
		return album;
	}
	
	public void setAlbum(Album album) {
		this.album = album;
	}
}
