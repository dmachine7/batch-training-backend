package com.bankapp.app.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bankapp.app.controller.AccountController;
import com.bankapp.app.model.Account;
import com.bankapp.app.service.AccountImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountImplementation accountServiceProvider;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void getAllAccountsTest() throws Exception {
    	Account account1 = new Account("1000", "atul", 155, "at", "atul123", 1000);
    	Account account2 = new Account("1000", "devang", 155, "dev", "dev123", 1000);
    	Account account3 = new Account("1000", "sahil", 155, "sah", "123", 1000);
        List<Account> accounts = Arrays.asList(account1, account2, account3);

        when(accountServiceProvider.getAllLogin()).thenReturn(accounts);

        mockMvc.perform(get("/api/account/getAll"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()", is(accounts.size())))
               .andExpect(jsonPath("$[0].user_id", is(accounts.get(0).getUser_id())))
               .andExpect(jsonPath("$[1].user_id", is(accounts.get(1).getUser_id())))
               .andExpect(jsonPath("$[2].user_id", is(accounts.get(2).getUser_id())))
               .andExpect(jsonPath("$[0].log_pass", is(accounts.get(0).getLog_pass())))
               .andExpect(jsonPath("$[1].log_pass", is(accounts.get(1).getLog_pass())))
               .andExpect(jsonPath("$[2].log_pass", is(accounts.get(2).getLog_pass())));
        
        verify(accountServiceProvider, times(1)).getAllLogin();
    }

    @Test
    public void getAccountByIdTest() throws Exception {
        Account account = new Account("1000", "rahul", 155, "rah", "rah123", 1000);
        int accountId = 1;

        when(accountServiceProvider.getById(accountId)).thenReturn(Optional.of(account));

        mockMvc.perform(get("/api/account/{id}", accountId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.user_id", is(account.getUser_id())))
               .andExpect(jsonPath("$.acc_no", is(account.getAcc_no())))
               .andExpect(jsonPath("$.customer_id", is(account.getCustomer_id())))
               .andExpect(jsonPath("$.log_pass", is(account.getLog_pass())))
               .andExpect(jsonPath("$.trans_pass", is(account.getTrans_pass())))
               .andExpect(jsonPath("$.balance", is(account.getBalance())));
        
        verify(accountServiceProvider, times(1)).getById(accountId);
    }

    @Test
    public void createAccountTest() throws Exception {
        Account newAccount = new Account("10000", "rohan", 155, "roh", "rohan123", 1000);
        
        mockMvc.perform(post("/api/account/sendData")
               .contentType(MediaType.APPLICATION_JSON)
               .content(new ObjectMapper().writeValueAsString(newAccount)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.user_id", is(newAccount.getUser_id())))
               .andExpect(jsonPath("$.acc_no", is(newAccount.getAcc_no())))
               .andExpect(jsonPath("$.customer_id", is(newAccount.getCustomer_id())))
               .andExpect(jsonPath("$.log_pass", is(newAccount.getLog_pass())))
               .andExpect(jsonPath("$.trans_pass", is(newAccount.getTrans_pass())))
               .andExpect(jsonPath("$.balance", is(newAccount.getBalance())));
       
        verify(accountServiceProvider, times(1)).saveLogin(any());
    }

    @Test
    public void updateAccountTest() throws Exception {
        Account existingAccount = new Account("1000", "atul", 155, "at", "atul123", 1000);
        Account updatedAccount = new Account("", "atul", 156, "atul", "atul1234", 1000);
        int accountId = 1;

        when(accountServiceProvider.getById(accountId)).thenReturn(Optional.of(existingAccount));
        when(accountServiceProvider.saveLogin(any())).thenReturn(updatedAccount);

        mockMvc.perform(put("/api/account/update/{id}", accountId)
               .contentType(MediaType.APPLICATION_JSON)
               .content(new ObjectMapper().writeValueAsString(updatedAccount)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.user_id", is(updatedAccount.getUser_id())))
               .andExpect(jsonPath("$.acc_no", is(updatedAccount.getAcc_no())))
               .andExpect(jsonPath("$.customer_id", is(updatedAccount.getCustomer_id())))
               .andExpect(jsonPath("$.log_pass", is(updatedAccount.getLog_pass())))
               .andExpect(jsonPath("$.trans_pass", is(updatedAccount.getTrans_pass())))
               .andExpect(jsonPath("$.balance", is(updatedAccount.getBalance())));

        verify(accountServiceProvider, times(1)).saveLogin(any());
    }

    @Test
    public void deleteAccountTest() throws Exception {
        Account existingAccount = new Account("1000", "atul", 155, "at", "atul123", 1000);
        int accountId = 1;

        when(accountServiceProvider.getById(accountId)).thenReturn(Optional.of(existingAccount));

        mockMvc.perform(delete("/api/account/remove/{id}", accountId))
               .andExpect(status().isOk());

        verify(accountServiceProvider, times(1)).remove_user(existingAccount);
    }
}
