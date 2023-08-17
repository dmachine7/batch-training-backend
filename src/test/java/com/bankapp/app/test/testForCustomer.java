package com.bankapp.app.test;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

import com.bankapp.app.controller.customer_controller;
import com.bankapp.app.model.customer_m;
import com.bankapp.app.repository.customer_repository;
import com.bankapp.app.service.customer_implementation;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = customer_controller.class )

@ActiveProfiles("test")
class testForCustomer {
	
	//private static final ObjectMapper Ob_map = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private customer_implementation customer_service_provider;
	
	@BeforeEach
	public void init() {
		customer_m customer = new customer_m(5, 
				303,
				"Mr.",
				"Chinmoi",
				"Chinmoi's Father",
				"1234567891",
				"chinmoi@gmail.com",
				"123443211234",
				new Date(), 
				"abc", 
				"abc",
				"employee",
				"self",
				"100",
				100);
		 when(customer_service_provider.getById(5)).thenReturn(Optional.of(customer));
	}

	 @Test
	    public void find_customer_by_id() throws Exception {
	
	        this.mockMvc.perform(get("/api/customer/5"))
	                .andDo(print())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.id", is(5)))/*
	                .andExpect(jsonPath("$.name", is("Book Name")))
	                .andExpect(jsonPath("$.author", is("Cts")))
	                .andExpect(jsonPath("$.price", is(9.99)))*/;
	
	        verify(customer_service_provider, times(1)).getById(5);
	    }
}




