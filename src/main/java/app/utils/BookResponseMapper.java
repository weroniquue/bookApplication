package app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import app.models.IndustryIdentifier;
import app.models.Item;
import app.payloads.BookResponse;

public class BookResponseMapper {

    public static String convertDate(String date) {

        SimpleDateFormat formatFull = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        try {

            if(date.length() == 4 ){
                Date formatedDate = formatYear.parse(date);
                return Long.toString(formatedDate.getTime());
            }else{
                Date formatedDate = formatFull.parse(date);
                return Long.toString(formatedDate.getTime());
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static BookResponse mapper(Item book) {

        BookResponse response = new BookResponse();

        String isbn = book.getVolumeInfo().getIndustryIdentifiers()
                .stream()
                .filter(identifier -> identifier.getType().equals(AppConst.DEFAULT_TYPE_ISBN))
                .findFirst()
                .map(IndustryIdentifier::getIdentifier)
                .orElse(book.getId());

        response.setIsbn(isbn);

        response.setTitle(book.getVolumeInfo().getTitle());
        response.setSubtitle(book.getVolumeInfo().getSubtitle());
        response.setSubtitle(book.getVolumeInfo().getPublisher());
        response.setPublishedDate(convertDate(book.getVolumeInfo().getPublishedDate()));
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
