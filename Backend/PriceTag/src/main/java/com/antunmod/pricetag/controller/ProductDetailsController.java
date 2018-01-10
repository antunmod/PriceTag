package com.antunmod.pricetag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.model.ProductDetails;
import com.antunmod.pricetag.repo.ProductDetailsRepository;

@RestController
@RequestMapping("/products")
public class ProductDetailsController {
	
	@Autowired
	private ProductDetailsRepository productDetailsRepository;
	
	@ResponseBody
	@GetMapping("")
	public ResponseEntity<ProductDetails> getProductForBarcode (@RequestParam("barcode") String barcode) {
		
		ProductDetails productDetails = productDetailsRepository.findProductForBarcode(barcode);
		if(productDetails==null) {
			return new ResponseEntity<ProductDetails>(new ProductDetails(), HttpStatus.OK);
		}
		return new ResponseEntity<ProductDetails>(productDetails, HttpStatus.OK);
	}

}
