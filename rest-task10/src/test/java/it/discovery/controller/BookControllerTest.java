package it.discovery.controller;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import it.discovery.bootstrap.RestApplication;
import it.discovery.repository.BookRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import org.hamcrest.Matchers;

@SpringJUnitWebConfig(RestApplication.class)
@AutoConfigureMockMvc
public class BookControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookRepository bookRepository;

	@Test
	@WithMockUser(username="root", authorities="ADMIN")
	public void getBooks_RepositoryEmpty_NoBooksReturned() throws Exception {

		BDDMockito.given(bookRepository.findAll())
		.willReturn(Collections.emptyList());
		mockMvc.perform(get("/book")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", Matchers.hasSize(0)));
	}

}
