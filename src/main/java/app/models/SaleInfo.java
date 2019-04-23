package app.models;

import java.util.List;

public class SaleInfo {
	
	private String country;
	private String saleability;
	private boolean isEbook;
	private String buyLink;
	private ListPrice listPrice;
	private RetailPrice retailPrice;
	private List<Offer> offers;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSaleability() {
		return saleability;
	}
	public void setSaleability(String saleability) {
		this.saleability = saleability;
	}
	public boolean isEbook() {
		return isEbook;
	}
	public void setEbook(boolean isEbook) {
		this.isEbook = isEbook;
	}
	public String getBuyLink() {
		return buyLink;
	}
	public void setBuyLink(String buyLink) {
		this.buyLink = buyLink;
	}
	public ListPrice getListPrice() {
		return listPrice;
	}
	public void setListPrice(ListPrice listPrice) {
		this.listPrice = listPrice;
	}
	public RetailPrice getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(RetailPrice retailPrice) {
		this.retailPrice = retailPrice;
	}
	public List<Offer> getOffers() {
		return offers;
	}
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	
	
}
