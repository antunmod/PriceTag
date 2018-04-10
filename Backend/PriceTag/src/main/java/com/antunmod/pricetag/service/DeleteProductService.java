package com.antunmod.pricetag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.ProductData;
import com.antunmod.pricetag.model.database.Price;
import com.antunmod.pricetag.model.database.Producer;
import com.antunmod.pricetag.model.database.Product;
import com.antunmod.pricetag.model.database.ProductSpecific;
import com.antunmod.pricetag.model.database.ProductStore;
import com.antunmod.pricetag.model.database.StoreSpecific;
import com.antunmod.pricetag.model.database.SubcategoryProduct;
import com.antunmod.pricetag.repo.PriceRepository;
import com.antunmod.pricetag.repo.ProducerRepository;
import com.antunmod.pricetag.repo.ProductRepository;
import com.antunmod.pricetag.repo.ProductSpecificRepository;
import com.antunmod.pricetag.repo.ProductStoreRepository;
import com.antunmod.pricetag.repo.StoreRepository;
import com.antunmod.pricetag.repo.StoreSpecificRepository;
import com.antunmod.pricetag.repo.SubcategoryProductRepository;

/*
 * This is the Services class which deletes data previously added to DB by tests.
 * Used by AddProductServiceTest.
 * All the methods return false if not all of the data which was supposed to be deleted was indeed
 * deleted from the database.
 */
@Service
public class DeleteProductService {


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
	@Autowired
	private StoreRepository storeRepository;

	private ProductData productData;
	
	public void setProductData(ProductData productData) {
		this.productData = productData;
	}
	/*
	 * This method will delete product_specific, product_store, price data.
	 */
	public Boolean deleteProductSpecific() {
		Price price = priceRepository.findByUserIdAndPriceChangeDate(productData.getBaseProduct().getUserId(), Price.getDateString());
		if (price == null)
			return false;
		priceRepository.delete(price);
		
		/*
		 * First get ProductSpecific to get productSpecificId out of it to identify the right ProductStore to delete.
		 */
		ProductSpecific productSpecific = productSpecificRepository.findByBarcode(productData.getBaseProduct().getBarcode());;
		if (productSpecific == null)
			return false;
		
		ProductStore productStore = productStoreRepository.findByProductSpecificIdAndStoreSpecificId(productSpecific.getProductSpecificId(), productData.getStoreSpecificId());
		if (productStore == null)
			return false;
		productStoreRepository.delete(productStore);
		
		
		productSpecificRepository.delete(productSpecific);

		return true;
	}

	/*
	 * This method will save call deleteProductSpecific to delete the data which should be deleted and delete product.
	 */
	public Boolean deleteProduct() {
		Boolean success = deleteProductSpecific();
		if(!success)
			return success;
		Product product = productRepository.findByProductNameAndProducerId(productData.getProductName(), productData.getProducerId());
		if (product == null)
			return false;
		SubcategoryProduct subcategoryProduct = subcategoryProductRepository.findByProductId(product.getProductId());
		if (subcategoryProduct == null)
			return false;
		subcategoryProductRepository.delete(subcategoryProduct);
		productRepository.delete(product);	
		return success;

	}

	/*
	 * This method will save call deleteProduct to delete the data which should be deleted and delete producer.
	 */
	public Boolean deleteProducer() {
		Producer producer = producerRepository.findByProducerName(productData.getProducerName());
		if (producer == null)
			return false;
		productData.setProducerId(producer.getProducerId());

		Boolean success = deleteProduct();
		if(!success)
			return success;
		
		producerRepository.delete(producer);
		return success;
	}

//	/*
//	 * This method will save new store_specific data and call saveProductSpecific
//	 * with the required data.
//	 */
//	public Boolean deleteStoreSpecificProductSpecific(AddStoreSpecificProductSpecific addStoreSpecificProductSpecific) {
//		StoreSpecific storeSpecific = storeSpecificRepository.save(new StoreSpecific(
//				addStoreSpecificProductSpecific.getStoreId(), addStoreSpecificProductSpecific.getStoreAddress()));
//		if (storeSpecific == null) {
//			return false;
//		}
//
//		/*
//		 * If the productSpecific wasn't saved, delete storeSpecific from database and
//		 * return false.
//		 */
//		if (!saveProductSpecific(
//				addStoreSpecificProductSpecific.toAddProductSpecific(storeSpecific.getStoreSpecificId()))) {
//			storeSpecificRepository.delete(storeSpecific);
//			return false;
//		}
//		return true;
//	}
//
//	/*
//	 * This method will save new store_specific data and call saveProduct
//	 * with the required data.
//	 */
//	public Boolean saveStoreSpecificProduct(AddStoreSpecificProduct addStoreSpecificProduct) {
//		StoreSpecific storeSpecific = storeSpecificRepository.save(
//				new StoreSpecific(addStoreSpecificProduct.getStoreId(), addStoreSpecificProduct.getStoreAddress()));
//		if (storeSpecific == null) {
//			return false;
//		}
//
//		/*
//		 * If the product wasn't saved, delete storeSpecific from database and
//		 * return false.
//		 */
//		if (!saveProduct(addStoreSpecificProduct.toAddProduct(storeSpecific.getStoreSpecificId()))) {
//			storeSpecificRepository.delete(storeSpecific);
//			return false;
//		}
//
//		return true;
//	}

	/*
	 * This method will call deleteProducer to delete the data which should be deleted and delete store_specific.
	 */
	public Boolean deleteStoreSpecificProducer() {
		StoreSpecific storeSpecific = storeSpecificRepository.findByStoreIdAndStoreAddress(productData.getStoreId(),
				productData.getStoreAddress());
		productData.setStoreSpecificId(storeSpecific.getStoreSpecificId());
		Boolean success = deleteProducer();
		if(!success)
			return success;
		
		storeSpecificRepository.delete(storeSpecific);

		return true;
	}
//
//	/*
//	 * This method will save new store data and call saveStoreSpecificProductSpecific
//	 * with the required data.
//	 */
//	public Boolean saveStoreProductSpecific(AddStoreProductSpecific addStoreProductSpecific) {
//		Store store = storeRepository.save(new Store(addStoreProductSpecific.getStoreName()));
//		if (store == null) {
//			return false;
//		}
//
//		/*
//		 * If the storeSpecificProductSpecific wasn't saved, delete store from database and
//		 * return false.
//		 */
//		if (!saveStoreSpecificProductSpecific(
//				addStoreProductSpecific.toAddStoreSpecificProductSpecific(store.getStoreId()))) {
//			storeRepository.delete(store);
//			return false;
//		}
//		return true;
//	}
//	/*
//	 * This method will save new store data and call saveStoreSpecificProduct
//	 * with the required data.
//	 */
//	public Boolean saveStoreProduct(AddStoreProduct addStoreProduct) {
//		Store store = storeRepository.save(new Store(addStoreProduct.getStoreName()));
//		if (store == null) {
//			return false;
//		}
//
//		/*
//		 * If the storeSpecificProduct wasn't saved, delete store from database and
//		 * return false.
//		 */
//		if (!saveStoreSpecificProduct(
//				addStoreProduct.toAddStoreSpecificProduct(store.getStoreId()))) {
//			storeRepository.delete(store);
//			return false;
//		}
//		return true;
//	}
//	
//	/*
//	 * This method will save new store data and call saveStoreSpecificProducer
//	 * with the required data.
//	 */
//	public Boolean saveStoreProducer(AddStoreProducer addStoreProducer) {
//		Store store = storeRepository.save(new Store(addStoreProducer.getStoreName()));
//		if (store == null) {
//			return false;
//		}
//
//		/*
//		 * If the storeSpecificProducer wasn't saved, delete store from database and
//		 * return false.
//		 */
//		if (!saveStoreSpecificProducer(
//				addStoreProducer.toAddStoreSpecificProducer(store.getStoreId()))) {
//			storeRepository.delete(store);
//			return false;
//		}
//		return true;
//	}
//
//	public Boolean savePrice(AddPrice addPrice) {
//		Short productStoreId = productStoreRepository.findProductStoreForProductSpecificIdAndStoreSpecificId(
//				addPrice.getProductSpecificId(), addPrice.getStoreSpecificId());
//		/*
//		 * What if there is no productStoreId for those values?
//		 */
//		
//		Price price = priceRepository.save(addPrice.toPrice(productStoreId));
//		/*
//		 * Return false if the price wasn't saved.
//		 */
//		if (price == null)
//			return false;
//		return true;
//	}
//
//	public Boolean saveStoreSpecificProductStore(AddStoreSpecificProductStore addStoreSpecificProductStore) {
//		StoreSpecific storeSpecific = storeSpecificRepository.save(
//				new StoreSpecific(addStoreSpecificProductStore.getStoreId(), addStoreSpecificProductStore.getStoreAddress()));
//		if (storeSpecific == null) {
//			return false;
//		}
//		
//		ProductStore productStore = productStoreRepository
//				.save(addStoreSpecificProductStore.toProductStore(storeSpecific.getStoreSpecificId()));
//		/*
//		 * ProductStore wasn't saved, delete previously added storeSpecific and return
//		 * false.
//		 */
//		if (productStore == null) {
//			storeSpecificRepository.delete(storeSpecific);
//			return false;
//		}
//		
//		Price price = priceRepository.save(addStoreSpecificProductStore.toPrice(productStore.getProductStoreId()));
//		/*
//		 * Price wasn't saved, remove productStore and storeSpecific from database and return false.
//		 */
//		if (price == null) {
//			productStoreRepository.delete(productStore);
//			storeSpecificRepository.delete(storeSpecific);
//			return false;
//		}
//		return true;
//		
//	}
//
//	public Boolean saveStoreProductStore(AddStoreProductStore addStoreProductStore) {
//		Store store = storeRepository.save(new Store(addStoreProductStore.getStoreName()));
//		if (store == null) {
//			return false;
//		}
//		
//		/*
//		 * If the storeSpecificProductStore wasn't saved, delete store from database and
//		 * return false.
//		 */
//		if (!saveStoreSpecificProductStore(
//				addStoreProductStore.toAddStoreSpecificProductStore(store.getStoreId()))) {
//			storeRepository.delete(store);
//			return false;
//		}
//		return true;
//	}
	
	
}
