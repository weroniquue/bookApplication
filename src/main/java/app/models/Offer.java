package app.models;

public class Offer {
	
	private int finskyOfferType;
    private ListPrice listPrice;
    private ListPrice retailPrice;
	
    public int getFinskyOfferType() {
		return finskyOfferType;
	}
	public void setFinskyOfferType(int finskyOfferType) {
		this.finskyOfferType = finskyOfferType;
	}
	public ListPrice getListPrice() {
		return listPrice;
	}
	public void setListPrice(ListPrice listPrice) {
		this.listPrice = listPrice;
	}
	public ListPrice getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(ListPrice retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	
    
    
}
