package app.service;

import java.util.*;
import java.util.stream.Collectors;

import app.exceptions.ResourceNotFoundException;
import app.models.*;
import app.payloads.AuthorRatingResponse;
import app.utils.AppConst;
import app.utils.Optionals;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import app.utils.JsonReader;


@Service
public class BookService {

    @Value("${jsonPath}")
    public String path;

    @Value("${api.url}")
    private String apiUrl;

    @Value("${fromUrl}")
    private boolean fromUrl;

    public Book getAllBooks(){
        return !fromUrl ? JsonReader.parseJson(this.path).get() : JsonReader.parseJsonFromUrl(this.apiUrl).get();
    }

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
                                .anyMatch(AppConst.DEFAULT_TYPE_ISBN::equals)
                ).findFirst();
    }

    public Optional<Item> getById(Book books, String isbn) {
        return books.getItems()
                .stream()
                .filter((book) -> book.getId().equals(isbn))
                .findAny();
    }

    public Optional<Item> getBookByIsbn(String isbn) {

        Book books = getAllBooks();

        return Optionals.or(
                () -> getByIsbn(books, isbn),
                () -> getById(books, isbn)
        );
    }

    public List<Item> getBooksByCategory(String category) {
        Book books = getAllBooks();

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



    public List<AuthorRatingResponse> getRating(){

        Book books = getAllBooks();

        List<String> authors = books.getItems()
                .stream()
                .filter(book -> Objects.nonNull(book.getVolumeInfo().getAuthors()))
                .map(Item::getVolumeInfo)
                .map(VolumeInfo::getAuthors)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());


        List<AuthorRatingResponse> responses = authors.stream()
                .map(author->{
                    OptionalDouble average = books.getItems()
                    .stream()
                    .filter(book -> Objects.nonNull(book.getVolumeInfo().getAuthors()))
                    .filter(book -> Objects.nonNull(book.getVolumeInfo().getAverageRating()))
                    .filter(book -> book.getVolumeInfo().getAuthors().contains(author))
                    .map(Item::getVolumeInfo)
                    .map(VolumeInfo::getAverageRating)
                    .mapToDouble(a->a)
                    .average();
                return average.isPresent() ?  new AuthorRatingResponse(author, average.getAsDouble()) : new AuthorRatingResponse(author,0.0);
         }).collect(Collectors.toList());

        return responses.stream()
                .filter(x->x.getAverageRating() > 0)
                .collect(Collectors.toList());
    }

}
