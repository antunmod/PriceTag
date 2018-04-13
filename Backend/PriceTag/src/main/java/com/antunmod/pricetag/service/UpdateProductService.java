package com.antunmod.pricetag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Price;
import com.antunmod.pricetag.model.database.ProductStore;
import com.antunmod.pricetag.model.database.Store;
import com.antunmod.pricetag.model.database.StoreSpecific;
import com.antunmod.pricetag.model.transfer.AddPrice;
import com.antunmod.pricetag.model.transfer.AddStoreProductStore;
import com.antunmod.pricetag.model.transfer.AddStoreSpecificProductStore;
import com.antunmod.pricetag.repo.PriceRepository;
import com.antunmod.pricetag.repo.ProductStoreRepository;
import com.antunmod.pricetag.repo.StoreRepository;
import com.antunmod.pricetag.repo.StoreSpecificRepository;

/*
 * This is the Services class for updating products.
 * Updating products includes updating product price, adding an existing product to 
 * a specific store and adding an existing product to a new store name specific store.
 */
@Service
public class UpdateProductService {

	@Autowired
	private ProductStoreRepository productStoreRepository;
	@Autowired
	private PriceRepository priceRepository;
	@Autowired
	private StoreSpecificRepository storeSpecificRepository;
	@Autowired
	private StoreRepository storeRepository;
	
	public Boolean savePrice(AddPrice addPrice) {
		Short productStoreId = productStoreRepository.findProductStoreForProductSpecificIdAndStoreSpecificId(
				addPrice.getProductSpecificId(), addPrice.getStoreSpecificId());
		/*
		 * What if there is no productStoreId for those values?
		 */
		
		Price price = priceRepository.save(addPrice.toPrice(productStoreId));
		/*
		 * Return false if the price wasn't saved.
		 */
		if (price == null)
			return false;
		return true;
	}

	public Boolean saveStoreSpecificProductStore(AddStoreSpecificProductStore addStoreSpecificProductStore) {
		StoreSpecific storeSpecific = storeSpecificRepository.save(
				new StoreSpecific(addStoreSpecificProductStore.getStoreId(), addStoreSpecificProductStore.getStoreAddress()));
		if (storeSpecific == null) {
			return false;
		}
		
		ProductStore productStore = productStoreRepository
				.save(addStoreSpecificProductStore.toProductStore(storeSpecific.getStoreSpecificId()));
		/*
		 * ProductStore wasn't saved, delete previously added storeSpecific and return
		 * false.
		 */
		if (productStore == null) {
			storeSpecificRepository.delete(storeSpecific);
			return false;
		}
		
		Price price = priceRepository.save(addStoreSpecificProductStore.toPrice(productStore.getProductStoreId()));
		/*
		 * Price wasn't saved, remove productStore and storeSpecific from database and return false.
		 */
		if (price == null) {
			productStoreRepository.delete(productStore);
			storeSpecificRepository.delete(storeSpecific);
			return false;
		}
		return true;
		
	}

	public Boolean saveStoreProductStore(AddStoreProductStore addStoreProductStore) {
		Store store = storeRepository.save(new Store(addStoreProductStore.getStoreName()));
		if (store == null) {
			return false;
		}
		
		/*
		 * If the storeSpecificProductStore wasn't saved, delete store from database and
		 * return false.
		 */
		if (!saveStoreSpecificProductStore(
				addStoreProductStore.toAddStoreSpecificProductStore(store.getStoreId()))) {
			storeRepository.delete(store);
			return false;
		}
		return true;
	}
	
}
