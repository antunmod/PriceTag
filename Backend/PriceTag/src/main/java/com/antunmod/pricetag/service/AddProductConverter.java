package com.antunmod.pricetag.service;

import com.antunmod.pricetag.model.AddProduct;
import com.antunmod.pricetag.model.Photo;
import com.antunmod.pricetag.model.database.Product;
import com.antunmod.pricetag.model.database.ProductSpecific;
import com.antunmod.pricetag.model.database.ProductStore;

public class AddProductConverter {

	private static final int FIRST_PRODUCT_UPDATE = 1;

	public static Product createProductFromAddProduct(AddProduct addProduct) {

		Product product = new Product();
		
		product.setProducer(addProduct.getProducer());
		product.setProductName(addProduct.getProductName());
		
		return product;
	}

	public static Photo createPhotoFromAddProduct(AddProduct addProduct) {

		Photo photo = new Photo();
		
		photo.setPhoto(addProduct.getPhoto());
		
		return photo;
	}

	public static ProductSpecific createProductSpecificFromAddProduct(AddProduct addProduct) {
		
		ProductSpecific productSpecific = new ProductSpecific();
		
		productSpecific.setBarcode(addProduct.getBarcode());
		productSpecific.setProductDesctiption(addProduct.getProductDescription());
		productSpecific.setProductSize(addProduct.getProductSize());
		productSpecific.setProductSizeId(addProduct.getProductSizeId());
		
		return productSpecific;
	}

	public static ProductStore createProductStoreFromAddProduct(AddProduct addProduct) {

		ProductStore productStore = new ProductStore();
		/*
		productStore.setStoreId(addProduct.getStoreId());
		productStore.setUserId(addProduct.getUserId());
		productStore.setPrice(addProduct.getPrice());
		productStore.setPriceChangeDate(addProduct.getPriceChangeDate());
		productStore.setAveragePrice(addProduct.getPrice());
		productStore.setProductUpdates(FIRST_PRODUCT_UPDATE);
		 */
		return productStore;
	}

}
