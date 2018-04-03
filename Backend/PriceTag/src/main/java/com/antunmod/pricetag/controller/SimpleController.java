package com.antunmod.pricetag.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antunmod.pricetag.model.AddProduct;
import com.antunmod.pricetag.model.Photo;
import com.antunmod.pricetag.model.database.Product;
import com.antunmod.pricetag.model.database.ProductSpecific;
import com.antunmod.pricetag.model.database.ProductStore;
import com.antunmod.pricetag.model.database.Store;
import com.antunmod.pricetag.model.database.SubcategoryProduct;
import com.antunmod.pricetag.repo.CategoryRepository;
import com.antunmod.pricetag.repo.CategorySubcategoryRepository;
import com.antunmod.pricetag.repo.PhotoRepository;
import com.antunmod.pricetag.repo.ProductRepository;
import com.antunmod.pricetag.repo.ProductSpecificRepository;
import com.antunmod.pricetag.repo.ProductStoreRepository;
import com.antunmod.pricetag.repo.SectorCategoryRepository;
import com.antunmod.pricetag.repo.SectorRepository;
import com.antunmod.pricetag.repo.SizeRepository;
import com.antunmod.pricetag.repo.StoreRepository;
import com.antunmod.pricetag.repo.SubcategoryProductRepository;
import com.antunmod.pricetag.repo.SubcategoryRepository;
import com.antunmod.pricetag.repo.SuggestedCategorizationRepository;
import com.antunmod.pricetag.service.AddProductConverter;

@RestController
public class SimpleController {

	private final Integer NOT_FOUND_INTEGER = -1;

	@Autowired
	private SectorRepository sectorRepository;

	@Autowired
	private SectorCategoryRepository sectorCategoryRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategorySubcategoryRepository categorySubcategoryRepository;

	@Autowired
	private SubcategoryRepository subcategoryRepository;

	@Autowired
	private SubcategoryProductRepository subcategoryProductRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductStoreRepository productStoreRepository;

	@Autowired
	private ProductSpecificRepository productSpecificRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private SizeRepository sizeRepository;

	@Autowired
	private PhotoRepository photoRepository;

	@Autowired
	private SuggestedCategorizationRepository suggestedCategorizationRepository;

	@ResponseBody
	@GetMapping("/sectors")
	public ResponseEntity<List<String>> getSectors() {
		List<String> sectorList = sectorRepository.getAllSectorNames();
		if (sectorList != null) {
			return new ResponseEntity<List<String>>(sectorList, HttpStatus.OK);

		}
		return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/categories")
	public ResponseEntity<List<String>> getCategoriesForSectorName(@RequestParam("sectorName") String sectorName) {
		List<String> categoryList = categoryRepository.findAllForSectorName(sectorName);
		if (categoryList != null) {
			return new ResponseEntity<List<String>>(categoryList, HttpStatus.OK);

		}
		return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/subcategories")
	public ResponseEntity<List<String>> getSubcategoriesForCategoryName(
			@RequestParam("categoryName") String categoryName) {
		List<String> subcategoryList = subcategoryRepository.findAllForCategoryName(categoryName);
		if (subcategoryList != null) {
			return new ResponseEntity<List<String>>(subcategoryList, HttpStatus.OK);

		}
		return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/subcategories/id")
	public ResponseEntity<Integer> getSubcategoryIdForCategoryAndSubcategoryName(
			@RequestParam("categoryName") String categoryName,
			@RequestParam("subcategoryName") String subcategoryName) {
		Integer subcategoryId = subcategoryRepository.findSubcategoryIdForCategoryAndSubcategoryName(categoryName,
				subcategoryName);
		if (subcategoryId != null) {
			return new ResponseEntity<Integer>(subcategoryId, HttpStatus.OK);

		}
		return new ResponseEntity<Integer>(NOT_FOUND_INTEGER, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/producers")
	public ResponseEntity<List<String>> getProducersForSubcategoryName(
			@RequestParam("subcategoryName") String subcategoryName) {
		List<String> producerList = productRepository.findAllForSubcategoryName(subcategoryName);
		if (producerList != null) {
			return new ResponseEntity<List<String>>(producerList, HttpStatus.OK);

		}
		return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/products/productNames")
	public ResponseEntity<List<String>> getProductNamesForSubcategoryNameAndProducer(
			@RequestParam("subcategoryName") String subcategoryName, @RequestParam("producer") String producer) {
		List<String> productList = productRepository.getProductNamesForSubcategoryNameAndProducer(subcategoryName,
				producer);
		if (productList != null) {
			return new ResponseEntity<List<String>>(productList, HttpStatus.OK);

		}
		return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/products/productId")
	public ResponseEntity<Integer> getProductIdForProducerAndProductName(@RequestParam("producer") String producer,
			@RequestParam("productName") String productName) {
		Integer productId = productRepository.getProductIdForProducerAndProductName(producer, productName);
		if (productId != null) {
			return new ResponseEntity<Integer>(productId, HttpStatus.OK);

		}
		return new ResponseEntity<Integer>(new Integer(NOT_FOUND_INTEGER), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/sizes/sizeValue")
	public ResponseEntity<List<String>> getSizeValuesForProductId(@RequestParam("productId") int productId) {

		List<String> sizeValues = productRepository.getSizeValuesForProductId(productId);
		if (sizeValues != null) {
			return new ResponseEntity<List<String>>(sizeValues, HttpStatus.OK);

		}
		return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/stores")
	public ResponseEntity<List<String>> getStoreNames() {
		List<Store> storeList = storeRepository.findAll();
		if (storeList == null) {
			return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.OK);
		}
		List<String> storeStringList = new ArrayList<>();
		for (int i = 0; i < storeList.size(); ++i) {
			String storeName = storeList.get(i).getStoreName();
			if (!storeStringList.contains(storeName)) {
				storeStringList.add(storeName);
			}
		}

		return new ResponseEntity<List<String>>(storeStringList, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/stores/locations")
	public ResponseEntity<List<String>> getStoreLocations(@RequestParam("storeName") String storeName) {
		List<String> storeLocationsList = storeRepository.getStoreLocations(storeName);
		if (storeLocationsList.isEmpty()) {
			return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.OK);
		}
		return new ResponseEntity<List<String>>(storeLocationsList, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping("/stores/address")
	public ResponseEntity<Integer> getStoreIdForAddress(@RequestParam("storeAddress") String storeAddress) {
		Integer storeId = storeRepository.findStoreIdForStoreAddress(storeAddress);
		if (storeId == null) {
			return new ResponseEntity<Integer>(NOT_FOUND_INTEGER, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(storeId, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping("/sizes")
	public ResponseEntity<List<String>> getSizeValues() {
		List<String> sizeTypeList = sizeRepository.getSizeTypes();
		if (sizeTypeList == null)
			return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.OK);
		return new ResponseEntity<List<String>>(sizeTypeList, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("products")
	public ResponseEntity<Boolean> addProduct(@RequestBody AddProduct addProduct) {

		Product product = AddProductConverter.createProductFromAddProduct(addProduct);
		Product savedProduct = productRepository.save(product);

		Photo photo = AddProductConverter.createPhotoFromAddProduct(addProduct);
		Photo savedPhoto = photoRepository.save(photo);

		ProductSpecific productSpecific = AddProductConverter.createProductSpecificFromAddProduct(addProduct);
		productSpecific.setProductId(savedProduct.getProductId());
		productSpecific.setPhotoId(savedPhoto.getPhotoId());
		ProductSpecific savedProductSpecific = productSpecificRepository.save(productSpecific);

		ProductStore productStore = AddProductConverter.createProductStoreFromAddProduct(addProduct);
		productStore.setProductSpecificId(savedProductSpecific.getProductSpecificId());
		ProductStore savedProductStore = productStoreRepository.save(productStore);

		SubcategoryProduct subcategoryProduct = new SubcategoryProduct();
		subcategoryProduct.setProductId(savedProduct.getProductId());
		subcategoryProduct.setSubcategoryId(addProduct.getSubcategoryId());
		SubcategoryProduct savedSubcategoryProduct = subcategoryProductRepository.save(subcategoryProduct);

		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("photos")
	public ResponseEntity<Integer> addPhoto(@RequestBody byte[] photoArray) {
		Photo photo, savedPhoto;
		photo = new Photo();
		photo.setPhoto(photoArray);
		savedPhoto = photoRepository.save(photo);

		return new ResponseEntity<Integer>(savedPhoto.getPhotoId(), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("photos/photo")
	public ResponseEntity<Byte[]> getPhoto(@RequestParam("photoId") int photoId) {

		Byte[] byteArray = photoRepository.getPhotoForPhotoId(photoId);

		return new ResponseEntity<Byte[]>(byteArray, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("subcategoryProduct")
	public ResponseEntity<Boolean> addSubcategoryProduct(@RequestParam("subcategoryName") String subcategoryName,
			@RequestParam("productId") int productId) {
		Integer subcategoryId = subcategoryProductRepository.getSubcategoryIdForSubcategoryName(subcategoryName);
		SubcategoryProduct subcategoryProduct = new SubcategoryProduct();
		subcategoryProduct.setProductId(productId);
		subcategoryProduct.setSubcategoryId(subcategoryId);

		subcategoryProductRepository.save(subcategoryProduct);

		return new ResponseEntity<Boolean>(new Boolean(true), HttpStatus.OK);
	}

}
