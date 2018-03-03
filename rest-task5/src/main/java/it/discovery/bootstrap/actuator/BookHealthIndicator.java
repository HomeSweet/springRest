package it.discovery.bootstrap.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import it.discovery.repository.BookRepository;

@Component
public class BookHealthIndicator implements HealthIndicator{

	private final BookRepository bookRepository;
	
	public BookHealthIndicator(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Health health() {
		if(bookRepository.isEmpty()) {
			return Health.down().withDetail("book_storage", 
					"empty").build();
		}
		return Health.up().build();
	}
	

}
