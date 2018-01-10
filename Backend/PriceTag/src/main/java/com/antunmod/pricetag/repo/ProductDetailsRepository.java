package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.ProductDetails;

@Service
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long>{

	// FIND PRODUCT FOR BARCODE
	@Query(value = "SELECT product_store_ID, " +
			"product.product_ID, " +
	        "subcategory_product.subcategory_ID, " +
	        "category_subcategory.category_ID, " +
	        "sector_ID, " +
	        "store_ID, " +
	        "user_ID, " +
	        "product_name, " +
	        "producer, " +
	        "barcode, " +
	        "photo, " +
	        "product_size, " +
	        "product_size_ID, " +
	        "price, " +
	        "price_change_date, " +
	        "average_price, " +
	        "product_updates " +
	        "FROM product_store LEFT JOIN product ON product_store.product_ID = product.product_ID JOIN " +
	        "subcategory_product ON product.product_ID = subcategory_product.product_ID JOIN " + 
	        "category_subcategory ON subcategory_product.subcategory_ID = category_subcategory.subcategory_ID JOIN " +
	        "sector_category ON category_subcategory.category_ID = sector_category.category_ID")
	ProductDetails findProductForBarcode(ProductDetails productDetails);

	
}
