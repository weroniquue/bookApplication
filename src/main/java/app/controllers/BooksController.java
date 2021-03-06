package app.controllers;

import app.payloads.AuthorRatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.exceptions.ResourceNotFoundException;
import app.models.Book;
import app.models.Item;
import app.payloads.BookResponse;
import app.service.BookService;
import app.utils.BookResponseMapper;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping("/")
public class BooksController {
	
	@Autowired
	private BookService bookService;

	@RequestMapping("/all")
    public Book getAllBooks() {
		return bookService.getAllBooks();
    }

	@RequestMapping("/isbn")
	public BookResponse getBookByIsbn(@RequestParam(value = "isbn") String isbn) {
		Item book = bookService
				.getBookByIsbn(isbn)
				.orElseThrow(() -> new ResourceNotFoundException("Book", "isbn", isbn));
		return BookResponseMapper.mapper(book);
	}

	@RequestMapping("/category")
	public List<BookResponse> getBooksByCategory(@RequestParam(value = "category") String category) {

		return bookService.getBooksByCategory(category)
				.stream()
				.map(BookResponseMapper::mapper)
				.collect(Collectors.toList());

	}

    @RequestMapping("/availableCategory")
	public List<String> availableCategory(){
	    return bookService.availableCategory();
    }

	@RequestMapping("/rating")
	public List<AuthorRatingResponse> getRating(){
		return bookService.getRating();
	}



}
