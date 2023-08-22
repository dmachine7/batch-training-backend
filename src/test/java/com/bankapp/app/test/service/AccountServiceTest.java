package com.bankapp.app.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.bankapp.app.model.account_m;
import com.bankapp.app.repository.account_repository;
import com.bankapp.app.service.account_implementation;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private account_implementation accountService;

    @Mock
    private account_repository accountRepository;

    @Test
    public void saveLoginTest() {
        account_m account = new account_m(1000, "atul", 155, "at", "atul123", 1000);
        
        when(accountRepository.save(any(account_m.class))).thenReturn(account);
        
        account_m savedAccount = accountService.saveLogin(account);
        assertNotNull(savedAccount);
        assertEquals(account.getAcc_no(), savedAccount.getAcc_no()); 
        assertEquals(account.getUser_id(), savedAccount.getUser_id()); 
        assertEquals(account.getCustomer_id(), savedAccount.getCustomer_id());   
        assertEquals(account.getLog_pass(), savedAccount.getLog_pass());  
        assertEquals(account.getBalance(), savedAccount.getBalance());
        
        verify(accountRepository, times(1)).save(eq(account));
    }

    @Test
    public void getAllLoginTest() {
    	account_m account1 = new account_m(1000, "atul", 155, "at", "atul123", 1000);
    	account_m account2 = new account_m(1000, "devang", 155, "dev", "dev123", 1000);
    	account_m account3 = new account_m(1000, "sahil", 155, "sah", "123", 1000);
    	List<account_m> accounts = Arrays.asList(account1, account2, account3);
    	
        when(accountRepository.findAll()).thenReturn(accounts);

        List<account_m> retrievedAccounts = accountService.getAllLogin();
        assertEquals(accounts.size(), retrievedAccounts.size());
        for (int i = 0; i < accounts.size(); i++) {
            account_m expectedAccount = accounts.get(i);
            account_m retrievedAccount = retrievedAccounts.get(i);
            assertEquals(expectedAccount.getAcc_no(), retrievedAccount.getAcc_no());
            assertEquals(expectedAccount.getUser_id(), retrievedAccount.getUser_id());
            assertEquals(expectedAccount.getCustomer_id(), retrievedAccount.getCustomer_id());
            assertEquals(expectedAccount.getLog_pass(), retrievedAccount.getLog_pass());
            assertEquals(expectedAccount.getTrans_pass(), retrievedAccount.getTrans_pass());
            assertEquals(expectedAccount.getBalance(), retrievedAccount.getBalance());
        }
        
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    public void getByIdTest() {
        int accountId = 1;
        account_m account = new account_m(1000, "atul", 155, "at", "atul123", 1000);
        
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        Optional<account_m> retrievedAccount = accountService.getById(accountId);
        assertTrue(retrievedAccount.isPresent());
        assertEquals(account.getUser_id(), retrievedAccount.get().getUser_id());
        assertEquals(account.getAcc_no(), retrievedAccount.get().getAcc_no());
        assertEquals(account.getUser_id(), retrievedAccount.get().getUser_id());
        assertEquals(account.getCustomer_id(), retrievedAccount.get().getCustomer_id());
        assertEquals(account.getLog_pass(), retrievedAccount.get().getLog_pass());
        assertEquals(account.getTrans_pass(), retrievedAccount.get().getTrans_pass());
        assertEquals(account.getBalance(), retrievedAccount.get().getBalance());
        verify(accountRepository, times(1)).findById(accountId);
    }

    @Test
    public void removeUserTest() {
        account_m account = new account_m(1000, "atul", 155, "at", "atul123", 1000);

        accountService.remove_user(account);
        verify(accountRepository, times(1)).delete(account);
    }
}

