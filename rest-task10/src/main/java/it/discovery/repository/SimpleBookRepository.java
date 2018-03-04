package it.discovery.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import it.discovery.model.Book;

@Repository
public class SimpleBookRepository implements BookRepository {
	private final Map<Integer, Book> books = new HashMap<>();

	private int counter = 0;
	
	private final Counter booksCounter;
	
	public SimpleBookRepository(MeterRegistry meterRegistry) {
		booksCounter = meterRegistry.counter("saved.books");
	}

	@Override
	public Book findById(int id) {
		return books.get(id);
	}

	@Override
	public List<Book> findAll() {
		return new ArrayList<>(books.values());
	}

	@Override
	public void save(Book book) {
		booksCounter.increment();
		if (book.getId() == 0) {
			counter++;
			book.setId(counter);
			books.put(counter, book);
			System.out.println("*** Book with id=" + book.getId() + " was created");
		} else {
			books.put(book.getId(), book);
			System.out.println("*** Book with id=" + book.getId() + " was updated");
		}
	}

	@Override
	public boolean delete(int id) {
		if (!books.containsKey(id)) {
			return false;
		}

		books.remove(id);
		System.out.println("*** Book with id=" + id + " was deleted");
		return true;
	}

	@Override
	public boolean isEmpty() {
		return books.isEmpty();
	}

}
