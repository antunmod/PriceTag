package com.antunmod.pricetag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antunmod.pricetag.model.database.Price;
import com.antunmod.pricetag.model.database.Producer;
import com.antunmod.pricetag.model.database.Product;
import com.antunmod.pricetag.model.database.ProductSpecific;
import com.antunmod.pricetag.model.database.ProductStore;
import com.antunmod.pricetag.model.database.Store;
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
import com.antunmod.pricetag.repo.SizeRepository;
import com.antunmod.pricetag.repo.StoreRepository;
import com.antunmod.pricetag.repo.StoreSpecificRepository;
import com.antunmod.pricetag.repo.SubcategoryProductRepository;

/*
 * This is the Service class which adds new product data to database.
 */
@Service
public class AddProductService {

	@Autowired
	private UserService userService;
	
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
	@Autowired
	private SizeRepository sizeRepository;

	private Integer pointsToBeAwarded = 0;
	
	public AddProductService() {
	}

	/*
	 * This method will save new product_specific, product_store, price data.
	 */
	public Boolean saveProductSpecific(AddProductSpecific addProductSpecific) {
		Byte sizeId = sizeRepository.findSizeIdForSizeType(addProductSpecific.getBaseProduct().getSizeUnit());
		ProductSpecific productSpecific = productSpecificRepository.save(addProductSpecific.toProductSpecific(sizeId));
		/*
		 * ProductSpecific wasn't saved, return false.
		 */
		if (productSpecific == null)
			return false;

		ProductStore productStore = productStoreRepository
				.save(addProductSpecific.toProductStore(productSpecific.getId()));
		/*
		 * ProductStore wasn't saved, delete previously added productSpecific and return
		 * false.
		 */
		if (productStore == null) {
			productSpecificRepository.delete(productSpecific);
			return false;
		}

		Price price = priceRepository.save(addProductSpecific.toPrice(productStore.getId()));
		/*
		 * Price wasn't saved, delete previously added productSpecific and productStore
		 * and return false.
		 */
		if (price == null) {
			productStoreRepository.delete(productStore);
			productSpecificRepository.delete(productSpecific);
			return false;
		}
		
		pointsToBeAwarded += 3;
		userService.awardPointsToUser(addProductSpecific.getBaseProduct().getUserId(), pointsToBeAwarded);
		pointsToBeAwarded = 0;

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
				.save(new SubcategoryProduct(addProduct.getSubcategoryId(), product.getId()));

		if (subcategoryProduct == null) {
			productRepository.delete(product);
			return false;
		}

		pointsToBeAwarded += 2;
		
		/*
		 * If the product specific wasn't saved, delete product and subcategory product
		 * and return false.
		 */
		if (!saveProductSpecific(addProduct.toAddProductSpecific(product.getId()))) {
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
		Producer newProducer = new Producer(addProducer.getProducerName());
		Producer producer = producerRepository.save(newProducer);
		if (producer == null)
			return false;
		
		pointsToBeAwarded += 2;

		/*
		 * If the product wasn't saved, delete producer from database and return false.
		 */
		
		if (!saveProduct(addProducer.toAddProduct(producer.getId()))) {
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

		pointsToBeAwarded += 2;

		/*
		 * If the productSpecific wasn't saved, delete storeSpecific from database and
		 * return false.
		 */
		if (!saveProductSpecific(addStoreSpecificProductSpecific.toAddProductSpecific(storeSpecific.getId()))) {
			storeSpecificRepository.delete(storeSpecific);
			return false;
		}
		return true;
	}

	/*
	 * This method will save new store_specific data and call saveProduct with the
	 * required data.
	 */
	public Boolean saveStoreSpecificProduct(AddStoreSpecificProduct addStoreSpecificProduct) {
		StoreSpecific storeSpecific = storeSpecificRepository.save(
				new StoreSpecific(addStoreSpecificProduct.getStoreId(), addStoreSpecificProduct.getStoreAddress()));
		if (storeSpecific == null) {
			return false;
		}

		pointsToBeAwarded += 2;
		
		/*
		 * If the product wasn't saved, delete storeSpecific from database and return
		 * false.
		 */
		if (!saveProduct(addStoreSpecificProduct.toAddProduct(storeSpecific.getId()))) {
			storeSpecificRepository.delete(storeSpecific);
			return false;
		}

		return true;
	}

	/*
	 * This method will save new store_specific data and call saveProducer with the
	 * required data.
	 */
	public Boolean saveStoreSpecificProducer(AddStoreSpecificProducer addStoreSpecificProducer) {
		StoreSpecific storeSpecific = storeSpecificRepository.save(
				new StoreSpecific(addStoreSpecificProducer.getStoreId(), addStoreSpecificProducer.getStoreAddress()));
		if (storeSpecific == null) {
			return false;
		}
		
		pointsToBeAwarded += 2;
		
		/*
		 * If the producer wasn't saved, delete storeSpecific from database and return
		 * false.
		 */
		if (!saveProducer(addStoreSpecificProducer.toAddProducer(storeSpecific.getId()))) {
			storeSpecificRepository.delete(storeSpecific);
			return false;
		}

		return true;
	}

	/*
	 * This method will save new store data and call
	 * saveStoreSpecificProductSpecific with the required data.
	 */
	public Boolean saveStoreProductSpecific(AddStoreProductSpecific addStoreProductSpecific) {
		Store store = storeRepository.save(new Store(addStoreProductSpecific.getStoreName()));
		if (store == null) {
			return false;
		}

		pointsToBeAwarded += 2;
		
		/*
		 * If the storeSpecificProductSpecific wasn't saved, delete store from database
		 * and return false.
		 */
		if (!saveStoreSpecificProductSpecific(
				addStoreProductSpecific.toAddStoreSpecificProductSpecific(store.getStoreId()))) {
			storeRepository.delete(store);
			return false;
		}
		return true;
	}

	/*
	 * This method will save new store data and call saveStoreSpecificProduct with
	 * the required data.
	 */
	public Boolean saveStoreProduct(AddStoreProduct addStoreProduct) {
		Store store = storeRepository.save(new Store(addStoreProduct.getStoreName()));
		if (store == null) {
			return false;
		}

		pointsToBeAwarded += 2;

		/*
		 * If the storeSpecificProduct wasn't saved, delete store from database and
		 * return false.
		 */
		if (!saveStoreSpecificProduct(addStoreProduct.toAddStoreSpecificProduct(store.getStoreId()))) {
			storeRepository.delete(store);
			return false;
		}
		return true;
	}

	/*
	 * This method will save new store data and call saveStoreSpecificProducer with
	 * the required data.
	 */
	public Boolean saveStoreProducer(AddStoreProducer addStoreProducer) {
		Store store = storeRepository.save(new Store(addStoreProducer.getStoreName()));
		if (store == null) {
			return false;
		}

		pointsToBeAwarded += 2;

		/*
		 * If the storeSpecificProducer wasn't saved, delete store from database and
		 * return false.
		 */
		if (!saveStoreSpecificProducer(addStoreProducer.toAddStoreSpecificProducer(store.getStoreId()))) {
			storeRepository.delete(store);
			return false;
		}
		return true;
	}

	public Boolean savePrice(AddPrice addPrice) {
		Short productSpecificId = addPrice.getProductSpecificId();
		Short storeSpecificId = addPrice.getStoreSpecificId();
		Short productStoreId = productStoreRepository.findProductStoreForProductSpecificIdAndStoreSpecificId(
				productSpecificId, storeSpecificId);
		if (productStoreId == null) {
			ProductStore productStore = productStoreRepository.save(new ProductStore(productSpecificId, storeSpecificId));
			productStoreId = productStore.getId();
		}

		Price price = priceRepository.save(addPrice.toPrice(productStoreId));
		/*
		 * Return false if the price wasn't saved.
		 */
		if (price == null)
			return false;
		
		pointsToBeAwarded += 2;
		userService.awardPointsToUser(addPrice.getUserId(), pointsToBeAwarded);
		pointsToBeAwarded = 0;
		
		return true;
	}

	public Boolean saveStoreSpecificProductStore(AddStoreSpecificProductStore addStoreSpecificProductStore) {
		StoreSpecific storeSpecific = storeSpecificRepository.save(new StoreSpecific(
				addStoreSpecificProductStore.getStoreId(), addStoreSpecificProductStore.getStoreAddress()));
		if (storeSpecific == null) {
			return false;
		}

		ProductStore productStore = productStoreRepository
				.save(addStoreSpecificProductStore.toProductStore(storeSpecific.getId()));
		/*
		 * ProductStore wasn't saved, delete previously added storeSpecific and return
		 * false.
		 */
		if (productStore == null) {
			storeSpecificRepository.delete(storeSpecific);
			return false;
		}
		
		Price price = priceRepository.save(addStoreSpecificProductStore.toPrice(productStore.getId()));
		/*
		 * Price wasn't saved, remove productStore and storeSpecific from database and
		 * return false.
		 */
		if (price == null) {
			productStoreRepository.delete(productStore);
			storeSpecificRepository.delete(storeSpecific);
			return false;
		}
		
		pointsToBeAwarded += 4;
		userService.awardPointsToUser(addStoreSpecificProductStore.getUserId(), pointsToBeAwarded);
		pointsToBeAwarded = 0;
		
		return true;

	}

	public Boolean saveStoreProductStore(AddStoreProductStore addStoreProductStore) {
		Store store = storeRepository.save(new Store(addStoreProductStore.getStoreName()));
		if (store == null) {
			return false;
		}

		pointsToBeAwarded += 2;
		
		/*
		 * If the storeSpecificProductStore wasn't saved, delete store from database and
		 * return false.
		 */
		if (!saveStoreSpecificProductStore(addStoreProductStore.toAddStoreSpecificProductStore(store.getStoreId()))) {
			storeRepository.delete(store);
			return false;
		}
		return true;
	}
	
	public Short getProductSpecificIdForBarcode(String barcode) {
		return productSpecificRepository.findProductSpecificIdForBarcode(barcode);
	}

}
