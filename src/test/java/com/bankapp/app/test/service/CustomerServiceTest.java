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

import com.bankapp.app.model.Customer;
import com.bankapp.app.repository.CustomerRepository;
import com.bankapp.app.service.CustomerImplementation;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerImplementation customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void sampleTest() {
    	assertEquals("hello", "hello");
    }
    
    @Test
    public void saveLoginTest() {
        Customer customer = new Customer(1, "303", "Mr.", "atul", "atul's Father", "1234567891", "atul@gmail.com", "123443211234", "2001-01-01",
			    "abc", "abc", "employee", "self", 0);
       
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        
        Customer savedCustomer = customerService.saveLogin(customer);

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
        assertEquals(customer.getAccount_status(), savedCustomer.getAccount_status());
        assertEquals(customer.getGross_annual_income(), savedCustomer.getGross_annual_income());
 
        verify(customerRepository, times(1)).save(customer);
    }
    
    
    
    @Test
    public void getAllLoginTest() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer(1, "303", "Mr.", "atul", "atul's Father", "1234567891", "atul@gmail.com", "123443211234", "2001-01-01",
			    "abc", "abc", "employee", "self", 0);
	    Customer customer2 = new Customer(2, "303", "Mr.", "devang", "devang's Father", "2345678912", "devang@gmail.com", "023443211234", "2001-01-02",
				"def", "dec", "employee", "self", 0);
	    Customer customer3 = new Customer(4,"303", "Mr.", "sahil", "sahil's Father", "3456789123", "sahil@gmail.com", "013443211234", "2001-01-04",
				"ghi", "ghk", "employee", "self",  0);
	    customers = Arrays.asList(customer1, customer2, customer3);
	 
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> retrievedCustomers = customerService.getAllLogin();

        assertEquals(customers.size(), retrievedCustomers.size());
        for (int i = 0; i < customers.size(); i++) {
            Customer expectedCustomer = customers.get(i);
            Customer actualCustomer = retrievedCustomers.get(i);
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
            assertEquals(expectedCustomer.getAccount_status(), actualCustomer.getAccount_status());
            assertEquals(expectedCustomer.getGross_annual_income(), actualCustomer.getGross_annual_income());
        }
        
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void getByIdTest() {
        int customerId = 1;
        Customer customer = new Customer(1, "303", "Mr.", "atul", "atul's Father", "1234567891", "atul@gmail.com", "123443211234", "2001-01-01",
			    "abc", "abc", "employee", "self", 0);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> retrievedCustomer = customerService.getById(customerId);

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
        Customer customer = new Customer(1, "303", "Mr.", "atul", "atul's Father", "1234567891", "atul@gmail.com", "123443211234", "2001-01-01",
			    "abc", "abc", "employee", "self", 0);

        customerService.remove_user(customer);

        verify(customerRepository, times(1)).delete(customer);
    }

    @Test
    public void getCustomerAccTest() {
        String customerId = "303";
        Customer customer = new Customer(1, "303", "Mr.", "atul", "atul's Father", "1234567891", "atul@gmail.com", "123443211234", "2001-01-01",
			    "abc", "abc", "employee", "self", 0);
        when(customerRepository.getCustomerAcc(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> retrievedCustomer = customerService.getCustomerAcc(customerId);

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
