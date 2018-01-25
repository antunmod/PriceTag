package com.antunmod.pricetag.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.UpdateProduct;

@Service
public interface UpdateProductRepository extends JpaRepository<UpdateProduct, Long>{
	
	//FIND PRODUCT FOR BARCODE AND STOREID
	@Query(value = "SELECT product_store_ID, " + 
			"user_ID, " + 
			"photo_ID, " + 
			"producer, " + 
			"product_name, " + 
			"product_size, " + 
			"size_type, " + 
			"price, " + 
			"average_price, " + 
			"product_updates, " + 
			"price_change_date " + 
			"FROM product_store LEFT JOIN product ON product_store.product_ID = product.product_ID JOIN " + 
			"product_size ON product_store.product_size_ID = product_size.product_size_ID JOIN " + 
			"store on product_store.store_ID = store.store_ID WHERE " + 
			"barcode = ?1 AND store_address = ?2", nativeQuery=true)
	UpdateProduct findProductForBarcodeAndStoreAddress(String barcode, String storeAddress);
	
	
	/*@Query(value = "SELECT product_store_ID, " + 
			"user_ID, " + 
			"photo_ID, " + 
			"producer, " + 
			"product_name, " + 
			"product_size, " + 
			"size_type, " + 
			"price, " + 
			"average_price, " + 
			"product_updates, " + 
			"price_change_date " + 
			"FROM product_store LEFT JOIN product ON product_store.product_ID = product.product_ID JOIN " + 
			"product_size ON product_store.product_size_ID = product_size.product_size_ID JOIN " + 
			"store on product_store.store_ID = store.store_ID WHERE " + 
			"barcode = ?1", nativeQuery=true)
	UpdateProduct findProductForBarcode(String barcode);*/
	
}
