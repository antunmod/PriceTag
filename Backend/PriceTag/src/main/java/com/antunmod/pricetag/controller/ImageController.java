package com.antunmod.pricetag.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.model.transfer.SearchProductData;
import com.antunmod.pricetag.service.ImageService;

/*
 * This is a Controller class for handling photos.
 */
@RestController
@RequestMapping("/photos")
public class ImageController {
	
	@Autowired
	private ImageService photoService;
	
	@ResponseBody
	@PostMapping("")
	public ResponseEntity<Boolean> addImage(@RequestParam("encodedImage") String encodedImage, 
			@RequestParam("productSpecificId") Short productSpecificId) {
		Boolean success = photoService.saveImage(encodedImage, productSpecificId);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}

}
