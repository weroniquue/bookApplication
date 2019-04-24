package app.payloads;

import java.text.DecimalFormat;

public class AuthorRatingResponse {

    private String author;
    private double averageRating;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public AuthorRatingResponse(String author, double averageRating) {
        this.author = author;
        this.averageRating = averageRating;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
