package com.antunmod.pricetag.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "photo")
public class Photo implements Serializable {

	private static final long serialVersionUID = 2963303535006478944L;

	@Id
	@Column(name = "photo_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer photoId;

	@Column(name = "photo")
	private byte[] photo;

	public Photo() {
		super();
	}

	public Photo(Integer photoId, byte[] photo) {
		super();
		this.photoId = photoId;
		this.photo = photo;
	}

	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

}
