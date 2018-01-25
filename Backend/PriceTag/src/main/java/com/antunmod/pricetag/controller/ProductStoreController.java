package com.antunmod.pricetag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.model.ProductStore;
import com.antunmod.pricetag.model.UpdateProduct;
import com.antunmod.pricetag.repo.ProductStoreRepository;

@RestController
@RequestMapping("/productStore")
public class ProductStoreController {
	
	@Autowired
	private ProductStoreRepository productStoreRepository;
	
	@ResponseBody
	@PostMapping("/update")
	public ResponseEntity<Boolean> saveUpdatedProduct(@RequestBody UpdateProduct updateProduct) {
		
		int productUpdates = updateProduct.getProductUpdates();
		
		ProductStore productStore = productStoreRepository.findByProductStoreId(updateProduct.getProductStoreId());
		
		productStore.setAveragePrice(updateProduct.getAveragePrice());
		productStore.setPrice(updateProduct.getPrice());
		productStore.setPriceChangeDate(updateProduct.getPriceChangeDate());
		productStore.setProductUpdates(productUpdates);
		productStore.setUserId(updateProduct.getUserId());

		ProductStore editedProductStore = productStoreRepository.save(productStore);
		
		if(productUpdates==editedProductStore.getProductUpdates()) {
			return new ResponseEntity<Boolean> (true, HttpStatus.OK);
		}
		
		return new ResponseEntity<Boolean> (false, HttpStatus.OK);
		
	}
	
	@ResponseBody
	@PostMapping("")
	public ResponseEntity<Long> addProductStore(@RequestBody ProductStore productStore) {
		ProductStore savedProductStore;
		
		savedProductStore = productStoreRepository.saveAndFlush(productStore);
		
		
		
		return new ResponseEntity<Long>(savedProductStore.getProductStoreId(), HttpStatus.OK);
	}
	
//	@ResponseBody
//	@GetMapping("/photo")
//	public ResponseEntity<List<String>> getPhotoForProductIdAndSize(@RequestParam("productId") int productId,
//																	@RequestParam("size") String size) {
//		byte[] photo = productStoreRepository.getPhotoForProductIdAndSize(productId, size);
//		for(Size size: sizes) {
//			sizeList.add(size.getSizeType());
//		}
//		return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.OK);
//	}

}
