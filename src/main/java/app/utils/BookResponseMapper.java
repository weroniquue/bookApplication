package app.utils;

import java.util.Date;

import app.models.Item;
import app.payloads.BookResponse;

public class BookResponseMapper {
	
	/*TODO*/
	public static Date convertDate(String date) {
		
		return null;
	}
	
	public static BookResponse mapper(String isbn, Item book) {
		
		BookResponse response = new BookResponse();
		
		response.setIsbn(isbn);
		response.setTitle(book.getVolumeInfo().getTitle());
		response.setSubtitle(book.getVolumeInfo().getSubtitle());
		response.setSubtitle(book.getVolumeInfo().getPublisher());
		response.setPublishedDate(book.getVolumeInfo().getPublishedDate()); //do milisekund dwa formaty
		response.setDescription(book.getVolumeInfo().getDescription());
		response.setPageCount(book.getVolumeInfo().getPageCount());
		response.setThumbnailUrl(book.getVolumeInfo().getImageLinks().getThumbnail());
		response.setLanuage(book.getVolumeInfo().getLanguage());
		response.setPreviewLink(book.getVolumeInfo().getPreviewLink());
		response.setAuthors(book.getVolumeInfo().getAuthors());
		response.setCategories(book.getVolumeInfo().getCategories());
		
		
		return response;
		
	}
	
	
}
