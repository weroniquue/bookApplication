package app.models;

import java.util.List;

public class Book {
	
	private String requestedUrl;
	private List<Item> items;

	public Book(String requestedUrl,List<Item> items) {
		this.requestedUrl = requestedUrl;
		this.items = items;
	}

	public String getRequestedUrl() {
		return requestedUrl;
	}
	public void setRequestedUrl(String requestedUrl) {
		this.requestedUrl = requestedUrl;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	

}
