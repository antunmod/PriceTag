
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.antunmod.pricetag.repo.CategoryRepository;
import com.antunmod.pricetag.repo.CategorySubcategoryRepository;
import com.antunmod.pricetag.repo.PriceRepository;
import com.antunmod.pricetag.repo.ProducerRepository;
import com.antunmod.pricetag.repo.ProductRepository;
import com.antunmod.pricetag.repo.ProductSpecificRepository;
import com.antunmod.pricetag.repo.ProductStoreRepository;
import com.antunmod.pricetag.repo.SectorCategoryRepository;
import com.antunmod.pricetag.repo.SectorRepository;
import com.antunmod.pricetag.repo.SizeRepository;
import com.antunmod.pricetag.repo.StoreRepository;
import com.antunmod.pricetag.repo.StoreSpecificRepository;
import com.antunmod.pricetag.repo.SubcategoryProductRepository;
import com.antunmod.pricetag.repo.SubcategoryRepository;
import com.antunmod.pricetag.repo.UserRepository;

/*
 * This class tests repositories to get initial data from the database.
 */
public class RepositoryTest {
	
	@Autowired 
	CategoryRepository categoryRepository;
	@Autowired 
	CategorySubcategoryRepository categorySubcategoryRepository;
	@Autowired 
	PriceRepository priceRepository;
	@Autowired 
	ProducerRepository producerRepository;	
	@Autowired 
	ProductRepository productRepository;
	@Autowired 
	ProductSpecificRepository productSpecificRepository;
	@Autowired 
	ProductStoreRepository productStoreRepository;
	@Autowired 
	SectorCategoryRepository sectorCategoryRepository;
	@Autowired 
	SectorRepository sectorRepository;
	@Autowired 
	SizeRepository sizeRepository;
	@Autowired 
	StoreRepository storeRepository;
	@Autowired 
	StoreSpecificRepository storeSpecificRepository;
	@Autowired 
	SubcategoryProductRepository subcategoryProductRepository;
	@Autowired 
	SubcategoryRepository subcategoryRepository;
	@Autowired 
	UserRepository userRepository;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
