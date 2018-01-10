package com.antunmod.pricetag.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.model.Size;
import com.antunmod.pricetag.repo.SizeRepository;

@RestController
public class SimpleController {

	@Autowired
	private SizeRepository sizeRepository;
	
	@ResponseBody
	@GetMapping("/sizes")
	public ResponseEntity<List<String>> getSizeValues() {
		List<Size> sizes = sizeRepository.findAll();
		if (sizes == null) {
			return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.OK);
		}
		List<String> sizeList = new ArrayList<String>();
		for(Size size: sizes) {
			sizeList.add(size.getSizeType());
		}
		return new ResponseEntity<List<String>>(sizeList, HttpStatus.OK);
	}
	
}
