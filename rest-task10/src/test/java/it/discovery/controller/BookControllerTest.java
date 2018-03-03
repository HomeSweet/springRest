package it.discovery.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.discovery.bootstrap.RestApplication;

@SpringJUnitWebConfig(RestApplication.class)
public class BookControllerTest {
	@Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;
    
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(
        		applicationContext).build();
    }
    
    @Test
    public void test() { 
    	assertTrue(true);
    }

}

