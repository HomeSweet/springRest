package it.discovery.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import it.discovery.model.Book;

public class BookRestClient {
	private RestTemplate restTemplate 
	= new RestTemplate();
	
	private final String baseUrl;
	
	public BookRestClient(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Book[] findBooks() {
		return restTemplate.getForObject(baseUrl, 
				Book[].class);
	}
	
	public static void main(String[] args) {
		BookRestClient client = new BookRestClient("http://localhost:8080/book");
		System.out.println("Books found: " 
			+ Arrays.toString(client.findBooks()));
	}

}
