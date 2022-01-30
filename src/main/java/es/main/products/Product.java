package es.main.products;

/**
 * @author Fabbo
 *
 */
public class Product {
	private int cod_product;
	private String product_name;
	private String section;
	private double price;
	private String origin_country;
	
	public Product() {
		this.cod_product = 0;
		this.product_name = "";
		this.section = "";
		this.price = 0.0;
		this.origin_country = "";
	}
	
	public Product(int cP, String pN, String s, double p, 
					String oC) {
		this.cod_product = cP;
		this.product_name = pN;
		this.section = s;
		this.price = p;
		this.origin_country = oC;
	}

	/**
	 * @return the cod_product
	 */
	public int getCodProduct() {
		return cod_product;
	}

	/**
	 * @param cod_product the cod_product to set
	 */
	public void setCodProduct(int cod_product) {
		this.cod_product = cod_product;
	}

	/**
	 * @return the product_name
	 */
	public String getProductName() {
		return product_name;
	}

	/**
	 * @param product_name the product_name to set
	 */
	public void setProductName(String product_name) {
		this.product_name = product_name;
	}

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the origin_country
	 */
	public String getOriginCountry() {
		return origin_country;
	}

	/**
	 * @param origin_country the origin_country to set
	 */
	public void setOriginCountry(String origin_country) {
		this.origin_country = origin_country;
	}

	@Override
	public String toString() {
		return "Product [cod_product=" + cod_product + ", product_name=" 
				+ product_name + ", section=" + section + ", price=" 
				+ price + ", origin_country=" + origin_country + "]";
	}
}
