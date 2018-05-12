package antunmod.projects.pricetag.transfer;

/*
 * BaseProduct class contains basic information that is sent from application. The data it contains relates to most 
 * of the columns in product_specific table so new product_specific data is added.
 * 
 * This class is contained as a private variable in multiple classes which determine which 
 * new information, apart from addition to product_specific, should be saved to database.
 * 
 */
public class BaseProduct {

	private String barcode;
	private Short userId;
	private Float price;
	private String productDescription;
	private String photoURI;
	private Float productSize;
	private byte productSizeId;

	public BaseProduct() {}
	
	public BaseProduct(String barcode, Short userId, Float price, String productDescription, String photoURI,
			Float productSize, byte productSizeId) {
		super();
		this.barcode = barcode;
		this.userId = userId;
		this.price = price;
		this.productDescription = productDescription;
		this.photoURI = photoURI;
		this.productSize = productSize;
		this.productSizeId = productSizeId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getPhotoURI() {
		return photoURI;
	}

	public Float getProductSize() {
		return productSize;
	}

	public byte getProductSizeId() {
		return productSizeId;
	}

	public Short getUserId() {
		return userId;
	}

	public Float getPrice() {
		return price;
	}

}
