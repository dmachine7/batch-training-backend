package com.bankapp.app.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bankapp.app.controller.TransactionController;
import com.bankapp.app.model.Customer;
import com.bankapp.app.model.Transaction;
import com.bankapp.app.service.TransactionImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionImplementation transactionServiceProvider;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void getAllTransactionsTest() throws Exception {
    	Transaction transaction1 = new Transaction(1, "1234567890", "9876543210", "Transfer", "myPassword123", new Date(), 1000L, "Payment for services", "2023-12-31");
    	Transaction transaction2 = new Transaction(2, "1234567590", "98765432101", "Transfer", "myPassword123", new Date(), 10000L, "Payment for services", "2023-12-31");
    	Transaction transaction3 = new Transaction(3, "1234567690", "9666543210", "Transfer", "myPassword123", new Date(), 2000L, "Payment for services", "2023-12-31");
        List<Transaction> transactions = Arrays.asList(transaction1 ,transaction2, transaction3);
           
        when(transactionServiceProvider.getAllLogin()).thenReturn(transactions);
        //int trans_id, String send_acc, String rec_acc, String trans_type, String trans_pass, Date date, long amount, String remarks, String maturity_ins
        mockMvc.perform(get("/api/transaction/getAll"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()", is(transactions.size())))
               .andExpect(jsonPath("$[0].trans_id", is(transactions.get(0).getTrans_id())))
               .andExpect(jsonPath("$[1].trans_id", is(transactions.get(1).getTrans_id())))
               .andExpect(jsonPath("$[2].trans_id", is(transactions.get(2).getTrans_id())))
               .andExpect(jsonPath("$[0].send_acc", is(transactions.get(0).getSend_acc())))
               .andExpect(jsonPath("$[1].send_acc", is(transactions.get(1).getSend_acc())))
               .andExpect(jsonPath("$[2].send_acc", is(transactions.get(2).getSend_acc())));
        
        verify(transactionServiceProvider, times(1)).getAllLogin();

    }

    @Test
    public void getTransactionsByAccountIdTest() throws Exception {
    	Transaction transaction1 = new Transaction(1, "1234567890", "9876543210", "Transfer", "myPassword123", new Date(), 1000L, "Payment for services", "2023-12-31");
    	Transaction transaction2 = new Transaction(2, "1234567591", "98765432101", "Transfer", "myPassword123", new Date(), 10000L, "Payment for services", "2023-12-31");
    	Transaction transaction3 = new Transaction(3, "1234567690", "9666543210", "Transfer", "myPassword123", new Date(), 2000L, "Payment for services", "2023-12-31");
        List<Transaction> transactions = Arrays.asList(transaction1 ,transaction2, transaction3);
        int accountId = 1;

        when(transactionServiceProvider.getByAcc(accountId)).thenReturn(Optional.of(transactions));

        mockMvc.perform(get("/api/transaction/{id}", accountId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()", is(transactions.size())));
        
        verify(transactionServiceProvider, times(1)).getByAcc(accountId);         
    }

    @Test
    public void createTransactionTest() throws Exception {
        Transaction newTransaction =  new Transaction(4, "1234567890", "9876543210", "Transfer", "myPassword12345", new Date(), 1000L, "Payment for services", "2023-12-31");
        
        when(transactionServiceProvider.saveLogin(any())).thenReturn(newTransaction);
        
        mockMvc.perform(post("/api/transaction/sendData")
               .contentType(MediaType.APPLICATION_JSON)
               .content(new ObjectMapper().writeValueAsString(newTransaction)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.trans_id", is(newTransaction.getTrans_id())))
               .andExpect(jsonPath("$.send_acc", is(newTransaction.getSend_acc())))
               .andExpect(jsonPath("$.rec_acc", is(newTransaction.getRec_acc())))
               .andExpect(jsonPath("$.trans_type", is(newTransaction.getTrans_type())))
               .andExpect(jsonPath("$.trans_pass", is(newTransaction.getTrans_pass())))
               .andExpect(jsonPath("$.date", is(newTransaction.getDate().getTime()))) 
               .andExpect(jsonPath("$.amount", is((int) newTransaction.getAmount()))) 
               .andExpect(jsonPath("$.remarks", is(newTransaction.getRemarks())))
               .andExpect(jsonPath("$.maturity_ins", is(newTransaction.getMaturity_ins())));

        verify(transactionServiceProvider, times(1)).saveLogin(any());
    }

    @Test
    public void updateTransactionTest() throws Exception {
        Transaction existingTransaction = new Transaction(1, "1234567890", "9876543210", "Transfer", "myPassword123", new Date(), 1000L, "Payment for services", "2023-12-31");
        Transaction updatedTransaction = new Transaction(5, "1234567890", "9876543210", "Transfer", "myPassword", new Date(), 1050L, "Payment for services", "2023-12-31");
        int transactionId = 1;
        
        when(transactionServiceProvider.getById(transactionId)).thenReturn(Optional.of(existingTransaction));
        when(transactionServiceProvider.saveLogin(any())).thenReturn(updatedTransaction);

        mockMvc.perform(put("/api/transaction/update/{id}", transactionId)
               .contentType(MediaType.APPLICATION_JSON)
               .content(new ObjectMapper().writeValueAsString(updatedTransaction)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.trans_id", is(updatedTransaction.getTrans_id())))
               .andExpect(jsonPath("$.send_acc", is(updatedTransaction.getSend_acc())))
               .andExpect(jsonPath("$.rec_acc", is(updatedTransaction.getRec_acc())))
               .andExpect(jsonPath("$.trans_type", is(updatedTransaction.getTrans_type())))
               .andExpect(jsonPath("$.trans_pass", is(updatedTransaction.getTrans_pass())))
               .andExpect(jsonPath("$.date", is(updatedTransaction.getDate().getTime()))) 
               .andExpect(jsonPath("$.amount", is((int) updatedTransaction.getAmount())))    // long value can't be compared with int otherwise working
               .andExpect(jsonPath("$.remarks", is(updatedTransaction.getRemarks())))
               .andExpect(jsonPath("$.maturity_ins", is(updatedTransaction.getMaturity_ins())));

        verify(transactionServiceProvider, times(1)).getById(transactionId);
        verify(transactionServiceProvider, times(1)).saveLogin(any());
    }

    @Test
    public void deleteTransactionTest() throws Exception {
        Transaction existingTransaction = new Transaction(1, "1234567890", "9876543210", "Transfer", "myPassword123", new Date(), 1000L, "Payment for services", "2023-12-31");
        int transactionId = 1;

        when(transactionServiceProvider.getById(transactionId)).thenReturn(Optional.of(existingTransaction));

        mockMvc.perform(delete("/api/transaction/remove/{id}", transactionId))
               .andExpect(status().isOk());

        verify(transactionServiceProvider, times(1)).remove_user(existingTransaction);
    }
}
