package com.antunmod.pricetag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.model.UpdateProduct;
import com.antunmod.pricetag.repo.UpdateProductRepository;

@RestController
@RequestMapping("/products")
public class UpdateProductController {
	
	@Autowired
	private UpdateProductRepository updateProductRepository;
	
	@ResponseBody
	@GetMapping
	public ResponseEntity<UpdateProduct> findProductForBarcodeAndStoreId(@RequestParam("barcode") String barcode,
																			@RequestParam("storeId") int storeId) {
		
		UpdateProduct updateProduct = updateProductRepository.findProductForBarcodeAndStoreId(barcode, storeId);
		if(updateProduct==null) {
			return new ResponseEntity<UpdateProduct>(new UpdateProduct(), HttpStatus.OK);
		}
		return new ResponseEntity<UpdateProduct>(updateProduct, HttpStatus.OK);
	
	}
}
