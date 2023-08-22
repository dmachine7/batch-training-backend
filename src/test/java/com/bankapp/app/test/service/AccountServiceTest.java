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

import com.bankapp.app.model.Account;
import com.bankapp.app.repository.Account_repository;
import com.bankapp.app.service.Account_implementation;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private Account_implementation accountService;

    @Mock
    private Account_repository accountRepository;

    @Test
    public void saveLoginTest() {
        Account account = new Account(1000, "atul", 155, "at", "atul123", 1000);
        
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        
        Account savedAccount = accountService.saveLogin(account);
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
    	Account account1 = new Account(1000, "atul", 155, "at", "atul123", 1000);
    	Account account2 = new Account(1000, "devang", 155, "dev", "dev123", 1000);
    	Account account3 = new Account(1000, "sahil", 155, "sah", "123", 1000);
    	List<Account> accounts = Arrays.asList(account1, account2, account3);
    	
        when(accountRepository.findAll()).thenReturn(accounts);

        List<Account> retrievedAccounts = accountService.getAllLogin();
        assertEquals(accounts.size(), retrievedAccounts.size());
        for (int i = 0; i < accounts.size(); i++) {
            Account expectedAccount = accounts.get(i);
            Account retrievedAccount = retrievedAccounts.get(i);
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
        Account account = new Account(1000, "atul", 155, "at", "atul123", 1000);
        
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        Optional<Account> retrievedAccount = accountService.getById(accountId);
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
        Account account = new Account(1000, "atul", 155, "at", "atul123", 1000);

        accountService.remove_user(account);
        verify(accountRepository, times(1)).delete(account);
    }
}

