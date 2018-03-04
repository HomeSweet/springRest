package it.discovery.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.discovery.bootstrap.RestApplication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matchers;

@SpringJUnitWebConfig(RestApplication.class)
@AutoConfigureMockMvc
public class BookControllerTest {
	@Autowired
    private MockMvc mockMvc;
    
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void getBooks_RepositoryEmpty_NoBooksReturned() throws Exception { 
    	mockMvc.perform(get("/book"))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType(
    			MediaType.APPLICATION_JSON_UTF8_VALUE))
    	.andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

}

