package app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.exceptions.ResourceNotFoundException;
import app.models.Book;
import app.models.Item;
import app.service.BookService;
import app.utils.JsonReader;

@RestController
public class BooksContoller {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/all")
    public Book getAllBooks() {
		return new JsonReader().parseJson().get();		
    }
	
	
	@RequestMapping("/")
	public Item getBookByIsbn(@RequestParam(value = "isbn") String isbn) {
		
		return bookService
				.getBookByIsbn(isbn)
				.orElseThrow(() -> new ResourceNotFoundException("Book", "isbn", isbn));
		
	}

}
