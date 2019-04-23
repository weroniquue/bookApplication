package app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import app.models.Book;
import app.models.IndustryIdentifier;
import app.models.Item;
import app.utils.JsonReader;

@Service
public class BookService {
	
	public Optional<Item> getBookByIsbn(String isnb) {
		Book books = new JsonReader().parseJson().get();
		
		return Optional.of(
				books.getItems()
				.stream()
				.filter( (book) -> book.getId().equals(isnb))
				.findFirst()
				.orElse(
					books.getItems()
					.stream()
					.filter( book -> 
							book.getVolumeInfo().getIndustryIdentifiers()
							.stream()
							.map(IndustryIdentifier::getIdentifier)
							.anyMatch(identifier -> identifier.equals(isnb))
					)
					.findFirst().get()
				));
	}
	

}
