package it.discovery.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.discovery.model.Book;

@RestController
@RequestMapping("/book")
public class BookController {	
	
	@GetMapping(produces= {MediaType
			.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	public Book getEmptyBok() {
		return new Book();
	}


}
