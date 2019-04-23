package app.models;

import java.util.List;

public class Offer {
	private int finskyOfferType;
    private List<Price> listPrice;
    private List<Price> retailPrice;
	
    public int getFinskyOfferType() {
		return finskyOfferType;
	}
	public void setFinskyOfferType(int finskyOfferType) {
		this.finskyOfferType = finskyOfferType;
	}
	public List<Price> getListPrice() {
		return listPrice;
	}
	public void setListPrice(List<Price> listPrice) {
		this.listPrice = listPrice;
	}
	public List<Price> getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(List<Price> retailPrice) {
		this.retailPrice = retailPrice;
	}
    
    
}
