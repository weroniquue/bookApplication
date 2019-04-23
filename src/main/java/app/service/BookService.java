package app.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import app.utils.AppConst;
import app.utils.Optionals;
import org.springframework.stereotype.Service;

import app.models.Book;
import app.models.IndustryIdentifier;
import app.models.Item;
import app.utils.JsonReader;

@Service
public class BookService {

    public Optional<Item> getByIsbn(Book books, String isbn) {
        return books.getItems()
                .stream()
                .filter(book ->
                        book.getVolumeInfo().getIndustryIdentifiers()
                                .stream()
                                .map(IndustryIdentifier::getIdentifier)
                                .anyMatch(identifier -> identifier.equals(isbn))
                                && book.getVolumeInfo().getIndustryIdentifiers()
                                .stream()
                                .map(IndustryIdentifier::getType)
                                .anyMatch(type -> type.equals(AppConst.DEFAULT_TYPE_ISBN))
                ).findFirst();
    }

    public Optional<Item> getById(Book books, String isbn) {
        return books.getItems()
                .stream()
                .filter((book) -> book.getId().equals(isbn))
                .findAny();
    }

    public Optional<Item> getBookByIsbn(String isbn) {
        Book books = JsonReader.parseJson().get();

        return Optionals.or(
                () -> getByIsbn(books, isbn),
                () -> getById(books, isbn)
        );
    }

    public List<Item> getBooksByCategory(String category) {
        Book books = JsonReader.parseJson().get();

        return books.getItems()
                .stream()
                .filter(book -> Objects.nonNull(book.getVolumeInfo().getCategories()))
                .filter((book) ->
                        book.getVolumeInfo().getCategories()
                        .stream()
                        .anyMatch(cat -> cat.toLowerCase().equals(category.toLowerCase()))
                )
        .collect(Collectors.toList());

    }


}
