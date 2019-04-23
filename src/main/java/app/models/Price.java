package app.models;

public class Price {
	private double amountInMicros;
	private String currencyCode;
	
	public double getAmountInMicros() {
		return amountInMicros;
	}
	public void setAmountInMicros(double amountInMicros) {
		this.amountInMicros = amountInMicros;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	
}
