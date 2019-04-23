package app.service;

import java.util.Optional;

import app.utils.Optionals;
import org.springframework.stereotype.Service;

import app.models.Book;
import app.models.IndustryIdentifier;
import app.models.Item;
import app.utils.JsonReader;

@Service
public class BookService {


	public Optional<Item> getByIsbn(Book books, String isbn){
		return books.getItems()
				.stream()
				.filter( book ->
						book.getVolumeInfo().getIndustryIdentifiers()
								.stream()
								.map(IndustryIdentifier::getIdentifier)
								.anyMatch(identifier -> identifier.equals(isbn))
								&& book.getVolumeInfo().getIndustryIdentifiers()
										.stream()
										.map(IndustryIdentifier::getType)
										.anyMatch(type -> type.equals("ISBN_13"))
				).findFirst();
	}

	public Optional<Item> getById(Book books, String isbn){
		return books.getItems()
				.stream()
				.filter( (book) -> book.getId().equals(isbn))
				.findAny();
	}

	public Optional<Item> getBookByIsbn(String isbn) {
		Book books = new JsonReader().parseJson().get();

		return Optionals.or(
				()-> getByIsbn(books, isbn),
				() ->getById(books,isbn)
		);
	}
	

}
