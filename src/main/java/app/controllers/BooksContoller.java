package app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.models.Book;
import app.utils.JsonReader;

@RestController
public class BooksContoller {
	
	@RequestMapping("/")
    public Book getAllBooks() {
		return new JsonReader().parseJson().get();		
    }
	
	
	@RequestMapping("/isbn")
	public Book getBookByIsbn() {
		return null;
	}

}
