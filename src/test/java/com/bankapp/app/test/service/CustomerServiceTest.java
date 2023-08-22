package com.bankapp.app.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.bankapp.app.model.customer_m;
import com.bankapp.app.repository.customer_repository;
import com.bankapp.app.service.customer_implementation;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private customer_implementation customerService;

    @Mock
    private customer_repository customerRepository;

    @Test
    public void sampleTest() {
    	assertEquals("hello", "hello");
    }
    
    @Test
    public void saveLoginTest() {
        customer_m customer = new customer_m(1, 303, "Mr.", "atul", "atul's Father", "1234567891", "atul@gmail.com", "123443211234", "2001-01-01",
			    "abc", "abc", "employee", "self", "100", 100);
       
        when(customerRepository.save(any(customer_m.class))).thenReturn(customer);
        
        customer_m savedCustomer = customerService.saveLogin(customer);

        assertNotNull(savedCustomer);
        assertEquals(customer.getId(), savedCustomer.getId());
        assertEquals(customer.getAcc_no(), savedCustomer.getAcc_no());
        assertEquals(customer.getTitle(), savedCustomer.getTitle());
        assertEquals(customer.getName(), savedCustomer.getName());
        assertEquals(customer.getFather_name(), savedCustomer.getFather_name());
        assertEquals(customer.getMobile(), savedCustomer.getMobile());
        assertEquals(customer.getEmail(), savedCustomer.getEmail());
        assertEquals(customer.getAadhar(), savedCustomer.getAadhar());
        assertEquals(customer.getDob(), savedCustomer.getDob());
        assertEquals(customer.getPer_address(), savedCustomer.getPer_address());
        assertEquals(customer.getRes_address(), savedCustomer.getRes_address());
        assertEquals(customer.getOcc_type(), savedCustomer.getOcc_type());
        assertEquals(customer.getSource_income(), savedCustomer.getSource_income());
        assertEquals(customer.getGross_annual_income(), savedCustomer.getGross_annual_income());
        assertEquals(customer.getBalance(), savedCustomer.getBalance());
 
        verify(customerRepository, times(1)).save(customer);
    }
    
    
    
    @Test
    public void getAllLoginTest() {
        List<customer_m> customers = new ArrayList<>();
        customer_m customer1 = new customer_m(1, 303, "Mr.", "atul", "atul's Father", "1234567891", "atul@gmail.com", "123443211234", "2001-01-01",
			    "abc", "abc", "employee", "self", "100", 100);
	    customer_m customer2 = new customer_m(2, 303, "Mr.", "devang", "devang's Father", "2345678912", "devang@gmail.com", "023443211234", "2001-01-02",
				"def", "dec", "employee", "self", "1000", 1000);
	    customer_m customer3 = new customer_m(4, 303, "Mr.", "sahil", "sahil's Father", "3456789123", "sahil@gmail.com", "013443211234", "2001-01-04",
				"ghi", "ghk", "employee", "self", "200", 2000);
	    customers = Arrays.asList(customer1, customer2, customer3);
	 
        when(customerRepository.findAll()).thenReturn(customers);

        List<customer_m> retrievedCustomers = customerService.getAllLogin();

        assertEquals(customers.size(), retrievedCustomers.size());
        for (int i = 0; i < customers.size(); i++) {
            customer_m expectedCustomer = customers.get(i);
            customer_m actualCustomer = retrievedCustomers.get(i);
            assertEquals(expectedCustomer.getId(), actualCustomer.getId());
            assertEquals(expectedCustomer.getAcc_no(), actualCustomer.getAcc_no());
            assertEquals(expectedCustomer.getTitle(), actualCustomer.getTitle());
            assertEquals(expectedCustomer.getName(), actualCustomer.getName());
            assertEquals(expectedCustomer.getFather_name(), actualCustomer.getFather_name());
            assertEquals(expectedCustomer.getMobile(), actualCustomer.getMobile());
            assertEquals(expectedCustomer.getEmail(), actualCustomer.getEmail());
            assertEquals(expectedCustomer.getAadhar(), actualCustomer.getAadhar());
            assertEquals(expectedCustomer.getDob(), actualCustomer.getDob());
            assertEquals(expectedCustomer.getPer_address(), actualCustomer.getPer_address());
            assertEquals(expectedCustomer.getRes_address(), actualCustomer.getRes_address());
            assertEquals(expectedCustomer.getOcc_type(), actualCustomer.getOcc_type());
            assertEquals(expectedCustomer.getSource_income(), actualCustomer.getSource_income());
            assertEquals(expectedCustomer.getGross_annual_income(), actualCustomer.getGross_annual_income());
            assertEquals(expectedCustomer.getBalance(), actualCustomer.getBalance());
        }
        
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void getByIdTest() {
        int customerId = 1;
        customer_m customer = new customer_m(1, 303, "Mr.", "atul", "atul's Father", "1234567891", "atul@gmail.com", "123443211234", "2001-01-01",
			    "abc", "abc", "employee", "self", "100", 100);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<customer_m> retrievedCustomer = customerService.getById(customerId);

        assertTrue(retrievedCustomer.isPresent());
        assertEquals(customer.getId(), retrievedCustomer.get().getId());
        assertEquals(customer.getId(), retrievedCustomer.get().getId());
        assertEquals(customer.getAcc_no(), retrievedCustomer.get().getAcc_no());
        assertEquals(customer.getTitle(), retrievedCustomer.get().getTitle());
        assertEquals(customer.getName(), retrievedCustomer.get().getName());
        assertEquals(customer.getFather_name(), retrievedCustomer.get().getFather_name());
        assertEquals(customer.getMobile(), retrievedCustomer.get().getMobile());
        
        verify(customerRepository, times(1)).findById(customerId);      
    }

    @Test
    public void removeUserTest() {
        customer_m customer = new customer_m(1, 303, "Mr.", "atul", "atul's Father", "1234567891", "atul@gmail.com", "123443211234", "2001-01-01",
			    "abc", "abc", "employee", "self", "100", 100);

        customerService.remove_user(customer);

        verify(customerRepository, times(1)).delete(customer);
    }

    @Test
    public void getCustomerAccTest() {
        int customerId = 1;
        customer_m customer = new customer_m(1, 303, "Mr.", "atul", "atul's Father", "1234567891", "atul@gmail.com", "123443211234", "2001-01-01",
			    "abc", "abc", "employee", "self", "100", 100);
        when(customerRepository.getCustomerAcc(customerId)).thenReturn(Optional.of(customer));

        Optional<customer_m> retrievedCustomer = customerService.getCustomerAcc(customerId);

        assertTrue(retrievedCustomer.isPresent());
        assertEquals(customer.getId(), retrievedCustomer.get().getId());
        assertEquals(customer.getId(), retrievedCustomer.get().getId());
        assertEquals(customer.getId(), retrievedCustomer.get().getId());
        assertEquals(customer.getAcc_no(), retrievedCustomer.get().getAcc_no());
        assertEquals(customer.getTitle(), retrievedCustomer.get().getTitle());
        assertEquals(customer.getName(), retrievedCustomer.get().getName());
        assertEquals(customer.getFather_name(), retrievedCustomer.get().getFather_name());
        assertEquals(customer.getMobile(), retrievedCustomer.get().getMobile());
        
        verify(customerRepository, times(1)).getCustomerAcc(customerId);
    } 
}
