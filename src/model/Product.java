package model;

public class Product {
	private String articleName;
	private String section;
	private String price;
	private String originCountry;
	
	public Product() {
		this.articleName = "";
		this.section = "";
		this.price = "";
		this.originCountry = "";
	}
	
	public Product(String aN, String s, String p, String oC) {
		this.articleName = aN;
		this.section = s;
		this.price = p;
		this.originCountry = oC;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	
}
