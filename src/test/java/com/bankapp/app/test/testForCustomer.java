package com.bankapp.app.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bankapp.app.controller.customer_controller;
import com.bankapp.app.model.customer_m;
import com.bankapp.app.service.customer_implementation;
import com.bankapp.app.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = customer_controller.class )
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class testForCustomer {
	
	private static final ObjectMapper Ob_map = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private customer_implementation customer_service_provider;
	
	@MockBean
	JwtUtil jwtUtil;
	
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
				"2001-01-01", 
				"abc", 
				"abc",
				"employee",
				"self",
				"100",
				100,
				0);
		 when(customer_service_provider.getById(5)).thenReturn(Optional.of(customer));
		  
	}
	
	 @Test
	 public void find_all_customers() throws Exception {
		 List<customer_m> customer = new ArrayList<customer_m>();
		 customer.add(new customer_m(5, 
					303,
					"Mr.",
					"Chinmoi",
					"Chinmoi's Father",
					"1234567891",
					"chinmoi@gmail.com",
					"123443211234",
					"2001-01-01", 
					"abc", 
					"abc",
					"employee",
					"self",
					"100",
					100,
					0));
		 customer.add(new customer_m(6, 
					304,
					"Mr.",
					"Swagat",
					"Swagat's Father",
					"1234567891",
					"chinmoi@gmail.com",
					"123443211234",
					"2001-01-01", 
					"abc", 
					"abc",
					"unemployed",
					"self",
					"100",
					100,
					0));
		 when(customer_service_provider.getAllLogin()).thenReturn(customer);
		 
		 this.mockMvc.perform(get("/api/customer/getAll"))
         .andDo(print())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$", hasSize(2)))
         .andExpect(jsonPath("$[0].id", is(5)))
         .andExpect(jsonPath("$[0].acc_no", is(303)))
         .andExpect(jsonPath("$[0].name", is("Chinmoi")))
         .andExpect(jsonPath("$[0].father_name", is("Chinmoi's Father")))
         .andExpect(jsonPath("$[0].mobile", is("1234567891")))
         .andExpect(jsonPath("$[0].email", is("chinmoi@gmail.com")))
         .andExpect(jsonPath("$[0].aadhar", is("123443211234")))
         .andExpect(jsonPath("$[0].per_address", is("abc")))
         .andExpect(jsonPath("$[0].res_address", is("abc")))
         .andExpect(jsonPath("$[0].occ_type", is("employee")))
         .andExpect(jsonPath("$[0].source_income", is("self")))
         .andExpect(jsonPath("$[0].gross_annual_income", is("100")))
         .andExpect(jsonPath("$[0].balance", is(100)))
         .andExpect(jsonPath("$[0].account_status", is(0)))
		 .andExpect(jsonPath("$[1].id", is(6)))
         .andExpect(jsonPath("$[1].acc_no", is(304)))
         .andExpect(jsonPath("$[1].name", is("Swagat")))
         .andExpect(jsonPath("$[1].father_name", is("Swagat's Father")))
         .andExpect(jsonPath("$[1].mobile", is("1234567891")))
         .andExpect(jsonPath("$[1].email", is("chinmoi@gmail.com")))
         .andExpect(jsonPath("$[1].aadhar", is("123443211234")))
         .andExpect(jsonPath("$[1].per_address", is("abc")))
         .andExpect(jsonPath("$[1].res_address", is("abc")))
         .andExpect(jsonPath("$[1].occ_type", is("unemployed")))
         .andExpect(jsonPath("$[1].source_income", is("self")))
         .andExpect(jsonPath("$[1].gross_annual_income", is("100")))
         .andExpect(jsonPath("$[1].balance", is(100)))
         .andExpect(jsonPath("$[1].account_status", is(0)));

 verify(customer_service_provider, times(1)).getAllLogin();
		 
	 }
	 @Test
	    public void find_all_customers_error() throws Exception {
	
	        this.mockMvc.perform(get("/api/customer/getAll"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$", hasSize(0)));
	        
	 }

	 @Test
	    public void find_customer_by_id() throws Exception {
	
	        this.mockMvc.perform(get("/api/customer/5"))
	                .andDo(print())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.id", is(5)))
	                .andExpect(jsonPath("$.acc_no", is(303)))
	                .andExpect(jsonPath("$.name", is("Chinmoi")))
	                .andExpect(jsonPath("$.father_name", is("Chinmoi's Father")))
	                .andExpect(jsonPath("$.mobile", is("1234567891")))
	                .andExpect(jsonPath("$.email", is("chinmoi@gmail.com")))
	                .andExpect(jsonPath("$.aadhar", is("123443211234")))
	                .andExpect(jsonPath("$.per_address", is("abc")))
	                .andExpect(jsonPath("$.res_address", is("abc")))
	                .andExpect(jsonPath("$.occ_type", is("employee")))
	                .andExpect(jsonPath("$.source_income", is("self")))
	                .andExpect(jsonPath("$.gross_annual_income", is("100")))
	                .andExpect(jsonPath("$.balance", is(100)))
	                .andExpect(jsonPath("$.account_status", is(0)));
	
	        verify(customer_service_provider, times(1)).getById(5);
	    }
	
	 
	 @Test
	    public void find_customer_by_id_error() throws Exception {
	
	        this.mockMvc.perform(get("/api/customer/1"))
	        .andExpect(status().isNotFound());
	        
	 }
	 
	 @Test
	    public void find_customer_by_acc_no() throws Exception {
		 
		 customer_m customer = new customer_m(5, 
					303,
					"Mr.",
					"Chinmoi",
					"Chinmoi's Father",
					"1234567891",
					"chinmoi@gmail.com",
					"123443211234",
					"2001-01-01", 
					"abc", 
					"abc",
					"employee",
					"self",
					"100",
					100,
					0);
		 
		 when(customer_service_provider.getCustomerAcc(303))
		 .thenReturn(Optional.of(customer));
		 
		 
		 this.mockMvc.perform(get("/api/customer/getByAcc/303"))
		 .andDo(print())
         .andExpect(content().contentType(MediaType.APPLICATION_JSON))
         .andExpect(status().isOk())
		 .andExpect(jsonPath("$.id", is(5)));
		 
		 verify(customer_service_provider, times(1)).getCustomerAcc(303);	 
	 }
	 @Test
	    public void find_customer_by_acc_no_error() throws Exception {
		 this.mockMvc.perform(get("/api/customer/getByAcc/303"))
	        .andExpect(status().isNotFound());
		 
	 }
	 @Test
	    public void send_customer_data() throws Exception {
		 
		 customer_m customer = new customer_m(5, 
					303,
					"Mr.",
					"Chinmoi",
					"Chinmoi's Father",
					"1234567891",
					"chinmoi@gmail.com",
					"123443211234",
					"2001-01-01", 
					"abc", 
					"abc",
					"employee",
					"self",
					"100",
					100,
					0);

		 when(customer_service_provider.saveLogin(any(customer_m.class))).thenReturn(customer);
		 
		 this.mockMvc.perform(post("/api/customer/sendData")
		 .content(Ob_map.writeValueAsString(customer))
         .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.customer_data.id", is(5)))
         .andExpect(jsonPath("$.customer_data.acc_no", is(303)))
         .andExpect(jsonPath("$.customer_data.name", is("Chinmoi")))
         .andExpect(jsonPath("$.customer_data.father_name", is("Chinmoi's Father")))
         .andExpect(jsonPath("$.customer_data.mobile", is("1234567891")))
         .andExpect(jsonPath("$.customer_data.email", is("chinmoi@gmail.com")))
         .andExpect(jsonPath("$.customer_data.aadhar", is("123443211234")))
         .andExpect(jsonPath("$.customer_data.per_address", is("abc")))
         .andExpect(jsonPath("$.customer_data.res_address", is("abc")))
         .andExpect(jsonPath("$.customer_data.occ_type", is("employee")))
         .andExpect(jsonPath("$.customer_data.source_income", is("self")))
         .andExpect(jsonPath("$.customer_data.gross_annual_income", is("100")))
         .andExpect(jsonPath("$.customer_data.balance", is(100)))
         .andExpect(jsonPath("$.customer_data.account_status", is(0)));
		 
 verify(customer_service_provider, times(1)).saveLogin(any(customer_m.class));
	    }
	 
	 @Test
	    public void updating_customer() throws Exception {
		 
		 customer_m customer = new customer_m(5, 
					303,
					"Mr.",
					"Chinmoi",
					"Chinmoi's Father",
					"1234567891",
					"chinmoi@gmail.com",
					"123443211234",
					"2001-01-01", 
					"abc", 
					"abc",
					"employee",
					"self",
					"100",
					100,
					0);

	 }
	 
}




