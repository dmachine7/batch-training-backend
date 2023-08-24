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
import com.bankapp.app.repository.AccountRepository;
import com.bankapp.app.service.AccountImplementation;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountImplementation accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void saveLoginTest() {
        Account account = new Account("1000", "atul", "atul@mail.com", "at", "atul123", 1000,0,0);
        
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        
        Account savedAccount = accountService.saveLogin(account);
        assertNotNull(savedAccount);
        assertEquals(account.getAcc_no(), savedAccount.getAcc_no());
        assertEquals(account.getEmail(), savedAccount.getEmail());   
        assertEquals(account.getLog_pass(), savedAccount.getLog_pass());  
        assertEquals(account.getBalance(), savedAccount.getBalance());
        assertEquals(account.getAccount_status(),savedAccount.getAccount_status());
        
        verify(accountRepository, times(1)).save(eq(account));
    }

    @Test
    public void getAllLoginTest() {
    	Account account1 = new Account("1000", "atul", "atul123@mail.com", "at", "atul123", 1000,0,0);
    	Account account2 = new Account("1000", "devang", "dev123@mail.com", "dev", "dev123", 1000,0,0);
    	Account account3 = new Account("1000", "sahil","123@mail.com", "sah", "123", 1000,0,0);
    	List<Account> accounts = Arrays.asList(account1, account2, account3);
    	
        when(accountRepository.findAll()).thenReturn(accounts);

        List<Account> retrievedAccounts = accountService.getAllLogin();
        assertEquals(accounts.size(), retrievedAccounts.size());
        for (int i = 0; i < accounts.size(); i++) {
            Account expectedAccount = accounts.get(i);
            Account retrievedAccount = retrievedAccounts.get(i);
            assertEquals(expectedAccount.getAcc_no(), retrievedAccount.getAcc_no());
            assertEquals(expectedAccount.getEmail(), retrievedAccount.getEmail());
            assertEquals(expectedAccount.getLog_pass(), retrievedAccount.getLog_pass());
            assertEquals(expectedAccount.getTrans_pass(), retrievedAccount.getTrans_pass());
            assertEquals(expectedAccount.getBalance(), retrievedAccount.getBalance());
            assertEquals(expectedAccount.getAccount_status(),retrievedAccount.getAccount_status());
        }
        
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    public void getByIdTest() {
        String accountId = "1000";
        Account account = new Account("1000", "atul","atul123@mail.com", "at", "atul123", 1000L,0,0);
        
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        Optional<Account> retrievedAccount = accountService.getById(accountId);
        assertTrue(retrievedAccount.isPresent());
        assertEquals(account.getAcc_no(), retrievedAccount.get().getAcc_no());
        assertEquals(account.getEmail(), retrievedAccount.get().getEmail());
        assertEquals(account.getLog_pass(), retrievedAccount.get().getLog_pass());
        assertEquals(account.getTrans_pass(), retrievedAccount.get().getTrans_pass());
        assertEquals(account.getBalance(), retrievedAccount.get().getBalance());
        assertEquals(account.getAccount_status(),retrievedAccount.get().getAccount_status());
        verify(accountRepository, times(1)).findById(accountId);
    }

    @Test
    public void removeUserTest() {
        Account account = new Account("1000", "atul","atul123@mail.com", "at", "atul123", 1000L,0,0);

        accountService.remove_user(account);
        verify(accountRepository, times(1)).delete(account);
    }
}

