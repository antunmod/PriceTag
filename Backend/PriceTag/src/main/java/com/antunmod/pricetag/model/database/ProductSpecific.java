package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.antunmod.pricetag.model.transfer.BaseProduct;

/*
 * This class represents specifics of a product. It is neccessary for differentiating between different sizes of the 
 * same product (product with the same name and producer).
 */
@Entity
@Table(name = "product_specific")
public class ProductSpecific implements Serializable {

	private static final long serialVersionUID = -9196423082009556864L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	/*
	 * This variable references product_ID in product table.
	 */
	@Column(name = "product_id")
	private Short productId;

	@Column(name = "barcode")
	private String barcode;

	@Column(name = "description")
	private String description;

	/*
	 * Contains location of the folder which contains ProductSpecific thumbnail and
	 * photo.
	 */
	@Column(name = "photo_URI")
	private String photoURI;

	@Column(name = "size")
	private Float size;

	@Column(name = "size_id")
	private byte sizeId;

	public ProductSpecific() {}
	
	public ProductSpecific(BaseProduct baseProduct, Short productId) {
		this.productId = productId;
		this.barcode = baseProduct.getBarcode();
		this.description = baseProduct.getDescription();
		this.photoURI = baseProduct.getPhotoURI();
		this.size = baseProduct.getSize();
		this.sizeId = baseProduct.getSizeId();

	}

	public Short getId() {
		return id;
	}
	
	public String getBarcode() {
		return barcode;
	}

}
