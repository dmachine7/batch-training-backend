package com.bankapp.app.test;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bankapp.app.controller.CustomerController;
import com.bankapp.app.model.Customer;
import com.bankapp.app.service.CustomerImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CustomerControllerTest {
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();
	
	@Autowired
    private MockMvc mockMvc;
	
	@InjectMocks
	private CustomerController customerController;

    @Mock
    private CustomerImplementation customer_service_provider;
    
    private List<Customer> customers;
	
	@BeforeEach
	public void init() {
		 MockitoAnnotations.openMocks(this);
	     mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
		 Customer customer1 = new Customer( "atul@gmail.com", "303", "Mr.", "atul", "atul's Father", "1234567891", "123443211234", "2001-01-01",
				    "abc", "abc", "employee", "self", 0);
		 Customer customer2 = new Customer( "devang@gmail.com", "303", "Mr.", "devang", "devang's Father", "2345678912", "023443211234", "2001-01-02",
					"def", "dec", "employee", "self",0);
		 Customer customer3 = new Customer("sahil@gmail.com", "303", "Mr.", "sahil", "sahil's Father", "3456789123",  "013443211234", "2001-01-04",
					"ghi", "ghk", "employee", "self",0);
		 
		 customers = Arrays.asList(customer1, customer2, customer3);
	}
	
	@Test
    public void sampleTest() {
    	assertEquals("hello", "hello");
    }
	
	@Test
	public void getAllCustomerTest() throws Exception {
		when(customer_service_provider.getAllLogin()).thenReturn(customers);
        mockMvc.perform(get("/api/customer/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(3)))
                .andExpect(jsonPath("$[0].name", is("atul")))
                .andExpect(jsonPath("$[0].email", is("atul@gmail.com")))
                .andExpect(jsonPath("$[2].name", is("sahil")))
                .andExpect(jsonPath("$[2].email", is("sahil@gmail.com")));
        
        verify(customer_service_provider, times(1)).getAllLogin();

	}
	
	@Test
    public void getCustomerByIdTest() throws Exception {
		when(customer_service_provider.getById(anyString())).thenReturn(Optional.of(customers.get(2)));
        this.mockMvc.perform(get("/api/customer/sahil@gmail.com"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("sahil")))
                .andExpect(jsonPath("$.father_name", is("sahil's Father")))
                .andExpect(jsonPath("$.mobile", is("3456789123")))
                .andExpect(jsonPath("$.email", is("sahil@gmail.com")))
                .andExpect(jsonPath("$.aadhar", is("013443211234")))
                .andExpect(jsonPath("$.per_address", is("ghi")))
                .andExpect(jsonPath("$.res_address", is("ghk")))
                .andExpect(jsonPath("$.occ_type", is("employee")))
                .andExpect(jsonPath("$.gross_annual_income", is("self")));

        verify(customer_service_provider, times(1)).getById("sahil@gmail.com");
    }  
	
	@Test
	public void createCustomerTest() throws Exception {  		
		Customer newCustomer = new Customer( "rahul@gmail.com", "411", "Mr.", "rahul", "rahul's Father", "4567891234", "000043211234", "2001-01-05",
			    "pqr", "pqrs", "employee", "self",0);
        when(customer_service_provider.saveLogin(any(Customer.class))).thenReturn(newCustomer);
        
        mockMvc.perform(post("/api/customer/sendData")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newCustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(newCustomer.getName())))
                .andExpect(jsonPath("$.father_name", is(newCustomer.getFather_name())))
                .andExpect(jsonPath("$.mobile", is(newCustomer.getMobile())))
                .andExpect(jsonPath("$.email", is(newCustomer.getEmail())))
                .andExpect(jsonPath("$.aadhar", is(newCustomer.getAadhar())))
                .andExpect(jsonPath("$.per_address", is(newCustomer.getPer_address())))
                .andExpect(jsonPath("$.res_address", is(newCustomer.getRes_address())))
                .andExpect(jsonPath("$.occ_type", is(newCustomer.getOcc_type())))
                .andExpect(jsonPath("$.gross_annual_income", is(newCustomer.getGross_annual_income())));
        
        verify(customer_service_provider, times(1)).saveLogin(any(Customer.class));
        
	}
	    

	    @Test
	    public void updateCustomerTest() throws Exception {
	        Customer updatedCustomer = new Customer("rahul@gmail.com", "304", "Mr.", "rahul", "rahul's Father", "4567891234", "000043211234", "2001-01-05",
				    "pqr", "pqrs", "employee", "self", 0);
	        
	        when(customer_service_provider.getById(anyString())).thenReturn(Optional.of(updatedCustomer));
	        when(customer_service_provider.saveLogin(any())).thenReturn(updatedCustomer);
	        mockMvc.perform(put("/api/customer/update/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(updatedCustomer)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.name", is(updatedCustomer.getName())))
	                .andExpect(jsonPath("$.father_name", is(updatedCustomer.getFather_name())))
	                .andExpect(jsonPath("$.mobile", is(updatedCustomer.getMobile())))
	                .andExpect(jsonPath("$.email", is(updatedCustomer.getEmail())))
	                .andExpect(jsonPath("$.aadhar", is(updatedCustomer.getAadhar())))
	                .andExpect(jsonPath("$.per_address", is(updatedCustomer.getPer_address())))
	                .andExpect(jsonPath("$.res_address", is(updatedCustomer.getRes_address())))
	                .andExpect(jsonPath("$.occ_type", is(updatedCustomer.getOcc_type())))
	                .andExpect(jsonPath("$.gross_annual_income", is(updatedCustomer.getGross_annual_income())));
	        
	        verify(customer_service_provider, times(1)).getById(anyString());
	        verify(customer_service_provider, times(1)).saveLogin(any());

	    
	    }

	    @Test
	    public void deleteCustomerTest() throws Exception {
	    	Customer customerToRemove =  new Customer("sahil@gmail.com", "303", "Mr.", "sahil", "sahil's Father", "3456789123", "013443211234", "2001-01-04",
					"ghi", "ghk", "employee", "self",0);
	    	when(customer_service_provider.getById(anyString())).thenReturn(Optional.of(customerToRemove));
	        mockMvc.perform(delete("/api/customer/remove/sahil@gmail.com"))
	                .andExpect(status().isOk());
	        
	        verify(customer_service_provider, times(1)).getById("sahil@gmail.com");
	        verify(customer_service_provider, times(1)).remove_user(customerToRemove);
	    }
	}
	








