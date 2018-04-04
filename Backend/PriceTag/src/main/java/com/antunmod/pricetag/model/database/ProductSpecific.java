package com.antunmod.pricetag.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class represents specifics of a product. It is neccessary for differentiating between different sizes of the 
 * same product (product with the same name and producer).
 */
@Entity
@Table(name = "product_specific")
public class ProductSpecific implements Serializable {

	private static final long serialVersionUID = -9196423082009556864L;

	@Id
	@Column(name = "product_specific_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short productSpecificId;

	/*
	 * This variable references product_ID in product table.
	 */
	@Column(name = "product_ID")
	private short productId;

	@Column(name = "barcode")
	private String barcode;

	@Column(name = "product_description")
	private String productDesctiption;

	/*
	 * Contains location of the folder which contains ProductSpecific thumbnail and
	 * photo.
	 */
	@Column(name = "photo_URI")
	private String photoURI;

	@Column(name = "product_size")
	private float productSize;

	@Column(name = "product_size_ID")
	private byte productSizeId;

}
