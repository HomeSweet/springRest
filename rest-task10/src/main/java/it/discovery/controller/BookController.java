package it.discovery.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;
import javax.validation.Valid;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.discovery.controller.exception.BookValidationException;
import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping("/book")
public class BookController {
	
	private final BookRepository bookRepository;

	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@GetMapping(produces= {MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	public List<Resource<Book>> getBooks() {
		return bookRepository.findAll()
				.stream().map(book -> {
					Resource<Book> resource = new Resource<>(book);
					resource.add(linkTo(methodOn(BookController.class)
							.findBookById(book.getId())).withSelfRel());
					if(!book.isRented()) {
						resource.add(linkTo(methodOn(BookController.class)
								.rent(book.getId())).withRel("rent"));
					}
					return resource;
				}).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	//@Cacheable("books")
	@CacheResult(cacheName="books")
	public ResponseEntity<Book> findBookById(@PathVariable int id) {
		if(id <=0) {
			throw new BookValidationException(String.format("Book id is not valid: %s", id));
		}
		Book book = bookRepository.findById(id);
		if(book == null) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void saveBook(@Valid @RequestBody Book book) {
		bookRepository.save(book);
	}
	
	@PutMapping("/{id}")
	//@CachePut("books")
	@javax.cache.annotation.CachePut(cacheName="books")
	public void updateBook(@PathVariable int id,
			@Valid @RequestBody @CacheValue Book book) {
		bookRepository.save(book);
	}
	
	@PutMapping("/{id}/rent")
	public Book rent(@PathVariable int id) {
		Book book = bookRepository.findById(id);
		if(book == null) {
			throw new BookValidationException(
					String.format("Book %s not found", id));
		}
		if(book.isRented()) {
			throw new BookValidationException(
					String.format("Book %s is already rented", id));
		}
		book.setRented(true);
		bookRepository.save(book);
		
		return book;
	}

}
