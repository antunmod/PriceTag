package com.antunmod.pricetag.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.antunmod.pricetag.model.database.Price;
import com.antunmod.pricetag.model.database.Producer;
import com.antunmod.pricetag.model.database.Product;
import com.antunmod.pricetag.model.database.ProductSpecific;
import com.antunmod.pricetag.model.database.ProductStore;
import com.antunmod.pricetag.model.database.StoreSpecific;
import com.antunmod.pricetag.model.database.SubcategoryProduct;
import com.antunmod.pricetag.model.transfer.AddPrice;
import com.antunmod.pricetag.model.transfer.AddProducer;
import com.antunmod.pricetag.model.transfer.AddProduct;
import com.antunmod.pricetag.model.transfer.AddProductSpecific;
import com.antunmod.pricetag.model.transfer.AddStoreProducer;
import com.antunmod.pricetag.model.transfer.AddStoreProduct;
import com.antunmod.pricetag.model.transfer.AddStoreProductSpecific;
import com.antunmod.pricetag.model.transfer.AddStoreProductStore;
import com.antunmod.pricetag.model.transfer.AddStoreSpecificProducer;
import com.antunmod.pricetag.model.transfer.AddStoreSpecificProduct;
import com.antunmod.pricetag.model.transfer.AddStoreSpecificProductSpecific;
import com.antunmod.pricetag.model.transfer.AddStoreSpecificProductStore;
import com.antunmod.pricetag.repo.PriceRepository;
import com.antunmod.pricetag.repo.ProducerRepository;
import com.antunmod.pricetag.repo.ProductRepository;
import com.antunmod.pricetag.repo.ProductSpecificRepository;
import com.antunmod.pricetag.repo.ProductStoreRepository;
import com.antunmod.pricetag.repo.StoreSpecificRepository;
import com.antunmod.pricetag.repo.SubcategoryProductRepository;

/*
 * This is the Services class which saves the neccessary data to database.
 */
public class AddProductService {

	@Autowired
	private ProductSpecificRepository productSpecificRepository;
	@Autowired
	private ProductStoreRepository productStoreRepository;
	@Autowired
	private PriceRepository priceRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SubcategoryProductRepository subcategoryProductRepository;
	@Autowired
	private ProducerRepository producerRepository;
	@Autowired
	private StoreSpecificRepository storeSpecificRepository;

	/*
	 * This method will save new product_specific, product_store, price data.
	 */
	public Boolean saveProductSpecific(AddProductSpecific addProductSpecific) {
		ProductSpecific productSpecific = productSpecificRepository.save(addProductSpecific.toProductSpecific());
		/*
		 * ProductSpecific wasn't saved, return false.
		 */
		if (productSpecific == null)
			return false;

		ProductStore productStore = productStoreRepository
				.save(addProductSpecific.toProductStore(productSpecific.getProductSpecificId()));
		/*
		 * ProductStore wasn't saved, delete previously added productSpecific and return
		 * false.
		 */
		if (productStore == null) {
			productSpecificRepository.delete(productSpecific);
			return false;
		}

		Price price = priceRepository.save(addProductSpecific.toPrice(productStore.getProductStoreId()));
		/*
		 * Price wasn't saved, delete previously added productSpecific and productStore
		 * and return false.
		 */
		if (price == null) {
			productStoreRepository.delete(productStore);
			productSpecificRepository.delete(productSpecific);
			return false;
		}

		return true;
	}

	/*
	 * This method will save new product data and call saveProductSpecific with the
	 * required data. In the end it will create a new subcategory_product.
	 */
	public Boolean saveProduct(AddProduct addProduct) {
		Product product = productRepository.save(addProduct.toProduct());
		/*
		 * Product wasn't saved, return false.
		 */
		if (product == null)
			return false;

		SubcategoryProduct subcategoryProduct = subcategoryProductRepository
				.save(new SubcategoryProduct(addProduct.getSubcategoryId(), product.getProductId()));

		if (subcategoryProduct == null) {
			productRepository.delete(product);
			return false;
		}

		/*
		 * If the product specific wasn't saved, delete product and subcategory product
		 * and return false.
		 */
		if (!saveProductSpecific(addProduct.toAddProductSpecific(product.getProductId()))) {
			subcategoryProductRepository.delete(subcategoryProduct);
			productRepository.delete(product);
			return false;
		}

		return true;

	}

	/*
	 * This method will save new producer data and call saveProduct with the
	 * required data.
	 */
	public Boolean saveProducer(AddProducer addProducer) {
		Producer producer = producerRepository.save(new Producer(addProducer.getProducerName()));
		if (producer == null)
			return false;
		/*
		 * If the product wasn't saved, delete producer from database and return false.
		 */
		if (!saveProduct(addProducer.toAddProduct(producer.getProducerId()))) {
			producerRepository.delete(producer);
			return false;
		}
		return true;
	}

	/*
	 * This method will save new store_specific data and call saveProductSpecific
	 * with the required data.
	 */
	public Boolean saveStoreSpecificProductSpecific(AddStoreSpecificProductSpecific addStoreSpecificProductSpecific) {
		StoreSpecific storeSpecific = storeSpecificRepository.save(new StoreSpecific(
				addStoreSpecificProductSpecific.getStoreId(), addStoreSpecificProductSpecific.getStoreAddress()));
		if (storeSpecific == null) {
			return false;
		}

		/*
		 * If the storetSpecific wasn't saved, delete storeSpecific from database and
		 * return false.
		 */
		if (!saveProductSpecific(
				addStoreSpecificProductSpecific.toAddProductSpecific(storeSpecific.getStoreSpecificId()))) {
			storeSpecificRepository.delete(storeSpecific);
			return false;
		}
		return true;
	}

	public Boolean saveStoreSpecificProduct(AddStoreSpecificProduct addStoreSpecificProduct) {
		StoreSpecific storeSpecific = storeSpecificRepository.save(
				new StoreSpecific(addStoreSpecificProduct.getStoreId(), addStoreSpecificProduct.getStoreAddress()));
		if (storeSpecific == null) {
			return false;
		}

		/*
		 * If the storeSpecific wasn't saved, delete storeSpecific from database and
		 * return false.
		 */
		if (!saveProduct(addStoreSpecificProduct.toAddProduct(storeSpecific.getStoreSpecificId()))) {
			storeSpecificRepository.delete(storeSpecific);
			return false;
		}

		return true;
	}

	public Boolean saveStoreSpecificProducer(AddStoreSpecificProducer addStoreSpecificProducer) {
		StoreSpecific storeSpecific = storeSpecificRepository.save(
				new StoreSpecific(addStoreSpecificProducer.getStoreId(), addStoreSpecificProducer.getStoreAddress()));
		if (storeSpecific == null) {
			return false;
		}
		/*
		 * If the storeSpecific wasn't saved, delete storeSpecific from database and
		 * return false.
		 */
		if (!saveProducer(addStoreSpecificProducer.toAddProducer(storeSpecific.getStoreSpecificId()))) {
			storeSpecificRepository.delete(storeSpecific);
			return false;
		}

		return saveProducer(addStoreSpecificProducer.toAddProducer(storeSpecific.getStoreSpecificId()));
	}

	public Boolean saveStoreProductSpecific(AddStoreProductSpecific addStoreProductSpecific) {

		return true;
	}

	public Boolean saveStoreProduct(AddStoreProduct addStoreProduct) {

		return true;
	}

	public Boolean saveStoreProducer(AddStoreProducer addStoreProducer) {

		return true;
	}

	public Boolean savePrice(AddPrice addPrice) {

		return true;
	}

	public Boolean saveStoreSpecificProductStore(AddStoreSpecificProductStore addStoreSpecificProductStore) {

		return true;
	}

	public Boolean saveStoreProductStore(AddStoreProductStore addStoreProductStore) {

		return true;
	}
}
