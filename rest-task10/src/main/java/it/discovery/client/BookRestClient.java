package it.discovery.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import it.discovery.model.Book;

public class BookRestClient {
	private TestRestTemplate restTemplate 
	= new TestRestTemplate("root", "pwd");
	
	private final String baseUrl;
	
	public BookRestClient(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Book[] findBooks() {
		return restTemplate.getForObject(baseUrl, 
				Book[].class);
	}
	
	public ResponseEntity<Book> findBookById(int id) {
		return restTemplate.getForEntity(baseUrl + "/" + id, 
				Book.class);
	}
	
	public static void main(String[] args) {
		BookRestClient client = new BookRestClient("http://localhost:8080/book");
		System.out.println("Books found: " 
			+ Arrays.toString(client.findBooks()));
		System.out.println(client.findBookById(100));
	}

}
