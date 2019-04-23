package app.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.util.ResourceUtils;

import com.google.gson.Gson;

import app.models.Book;

public class JsonReader {

	private Gson gson;

	public JsonReader() {
		gson = new Gson();
	}

	public Optional<Book> parseJson() {

		try {

			File file = ResourceUtils.getFile("classpath:books.json");

			// Read File Content
			String content = new String(Files.readAllBytes(file.toPath()));

			Optional<Book> book = Optional.of(this.gson.fromJson(content, Book.class));
			
			return book;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/*TO DO*/
	public void parseJsonFromUrl() {

		try {
			URL url = new URL("http://");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
