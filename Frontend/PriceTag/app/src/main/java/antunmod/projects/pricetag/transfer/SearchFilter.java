package antunmod.projects.pricetag.transfer;

/*
 * The SearchFilter class contains filter data sent from frontend.
 */
public class SearchFilter {

	private String categoryName;
	private String subcategoryName;
	private String producerName;
	private String productName;
	private String storeName;

	/*
		Default constructor sets all filter values to empty string so they are ready for search on server.
	 */
	public SearchFilter() {
		this.categoryName = "";
		this.subcategoryName = "";
		this.producerName = "";
		this.productName = "";
		this.storeName = "";
	}

	public SearchFilter(String categoryName, String subcategoryName, String producerName, String productName,
			String storeName) {
		super();
		this.categoryName = categoryName;
		this.subcategoryName = subcategoryName;
		this.producerName = producerName;
		this.productName = productName;
		this.storeName = storeName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public String getProducerName() {
		return producerName;
	}

	public String getProductName() {
		return productName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}
