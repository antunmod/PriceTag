package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_size")
public class Size implements Serializable {

	private static final long serialVersionUID = -8253125849154997846L;

	@Id
	@Column(name = "product_size_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Byte productSizeId;

	@Column(name = "size_type")
	private String sizeType;

	public Size() {
	}

	public Size(Integer productSizeId, String sizeType) {
		super();
		this.productSizeId = productSizeId;
		this.sizeType = sizeType;
	}

	public Integer getProductSizeId() {
		return productSizeId;
	}

	public void setProductSizeId(Integer productSizeId) {
		this.productSizeId = productSizeId;
	}

	public String getSizeType() {
		return sizeType;
	}

	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
