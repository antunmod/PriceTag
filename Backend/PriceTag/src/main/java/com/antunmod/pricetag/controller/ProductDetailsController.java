package com.antunmod.pricetag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.model.ProductDetails;
import com.antunmod.pricetag.repo.ProductDetailsRepository;

@RestController
@RequestMapping("/product")
public class ProductDetailsController {
	
	@Autowired
	private ProductDetailsRepository productDetailsRepository;
	
	@ResponseBody
	@PostMapping("")
	public ResponseEntity<ProductDetails> getProductForBarcode (@RequestParam("barcode") String barcode) {
		return new ResponseEntity<ProductDetails>(new ProductDetails(), HttpStatus.OK);
	}

}
