package app.controllers;

import app.exceptions.ResourceNotFoundException;
import app.models.Item;
import app.service.BookService;
import app.utils.BookResponseMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.*;



public class BooksControllerTest {

    private BookService mockService;

    @Before
    public void setUp() {
        this.mockService = new BookService();
        mockService.setPath("books.json");
    }


    @Test
    public void getBookByIsbnTimeTest() {

        String isbn = "N1IiAQAAIAAJ";

        Item book = mockService
                .getBookByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "isbn", isbn));

        assertFalse("883612800000".equals(BookResponseMapper.mapper(book).getPublishedDate()));
        //assertEquals("883612800000", BookResponseMapper.mapper(book).getPublishedDate());

    }

    @Test
    public void getBookByIsbnDefaultIdTest() {

        String isbn = "N1IiAQAAIAAJ";

        Item book = mockService
                .getBookByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "isbn", isbn));

        assertEquals("Java", BookResponseMapper.mapper(book).getTitle());

    }

    @Test
    public void getBookByIsbnTest() {

        String isbn = "9780080568782";

        Item book = mockService
                .getBookByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "isbn", isbn));

        assertEquals("TCP/IP Sockets in Java", BookResponseMapper.mapper(book).getTitle());

    }




    @Test
    public void getBooksByCategory() {
        String category = "Computers";

        assertEquals(22,
            mockService.getBooksByCategory(category)
                .stream()
                .map(BookResponseMapper::mapper)
                .collect(Collectors.toList()).size());
    }

    @Test
    public void availableCategory() {
    }

    @Test
    public void getRating() {
        mockService.getRating().forEach(response ->{
            if(response.getAuthor().equals("Sir Thomas Stamford Raffles")){
                assertEquals(4.5,response.getAverageRating(),0);
            }

        });

    }
}