package com.antunmod.pricetag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.service.ImageService;

/*
 * This is a Controller class for handling photos.
 */
@RestController
@RequestMapping("/photos")
public class ImageController {
	
	@Autowired
	private ImageService imageService;
		
	@ResponseBody
	@PostMapping("")
	public ResponseEntity<Boolean> addImage(@RequestBody Byte[] imageArray, 
			@RequestParam("productSpecificId") Short productSpecificId) {
		Boolean success = imageService.saveImage(imageArray, productSpecificId);
		return new ResponseEntity<Boolean>(success, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("")
	public ResponseEntity<String> getByteArrayForProductSpecificId(@RequestParam("productSpecificId") Short productSpecificId) {
		String encodedImage = imageService.getByteArrayForProductSpecificId(productSpecificId);
		return new ResponseEntity<String>(encodedImage, HttpStatus.OK);
	}
}
