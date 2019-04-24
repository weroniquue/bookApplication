package app.utils;

import java.io.*;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import app.models.Item;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import com.google.gson.Gson;
import app.models.Book;

@Component
public final class JsonReader {

	public static Optional<Book> parseJson(String path) {

		try {
			Gson gson = new Gson();

			File file = ResourceUtils.getFile("classpath:"+path);//books.json");

			// Read File Content
			String content = new String(Files.readAllBytes(file.toPath()));

			Optional<Book> book = Optional.of(gson.fromJson(content, Book.class));

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
	public static Optional<Book> parseJsonFromUrl(String urlPath) {

		try {
			Gson gson = new Gson();
			URL url = new URL(urlPath);
			InputStreamReader reader = new InputStreamReader(url.openStream());
			JsonObject jobj = gson.fromJson(reader, JsonObject.class);

			Type listType = new TypeToken<ArrayList<Item>>(){}.getType();
			List<Item> yourClassList = new Gson().fromJson(jobj.get("items"), listType);

			return Optional.of(new Book(urlPath,yourClassList));

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
