package com.antunmod.pricetag.model.transfer;

/*
 * BaseProduct class contains basic information that is sent from application. The data it contains relates to most 
 * of the columns in product_specific table so new product_specific data is added.
 * 
 * This class is contained as a private variable in multiple classes which determine which 
 * new information, apart from addition to product_specific, should be saved to database.
 * 
 */
public class BaseProduct {

	private String barcode;
	private String productDescription;
	private String thumbnailURL;
	private String photoURL;
	private float productSize;
	private byte productSizeId;

}
