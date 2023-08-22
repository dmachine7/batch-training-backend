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
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.bankapp.app.model.Transaction;
import com.bankapp.app.repository.Transaction_repository;
import com.bankapp.app.service.Transaction_implementation;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    private Transaction_implementation transactionService;

    @Mock
    private Transaction_repository transactionRepository;

    @Test
    public void saveLoginTest() {
        Transaction transaction = new Transaction(1, "1234567890", "9876543210", "Transfer", "myPassword123", new Date(), 1000L, "Payment for services", "2023-12-31");
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction savedTransaction = transactionService.saveLogin(transaction);

        assertNotNull(savedTransaction);
        assertEquals(transaction.getTrans_id(), savedTransaction.getTrans_id());
        assertEquals(transaction.getSend_acc(), savedTransaction.getSend_acc());
        assertEquals(transaction.getRec_acc(), savedTransaction.getRec_acc());
        assertEquals(transaction.getTrans_type(), savedTransaction.getTrans_type());
        assertEquals(transaction.getTrans_pass(), savedTransaction.getTrans_pass());
        assertEquals(transaction.getDate(), savedTransaction.getDate());
        assertEquals(transaction.getAmount(), savedTransaction.getAmount());
        assertEquals(transaction.getRemarks(), savedTransaction.getRemarks());
        assertEquals(transaction.getMaturity_ins(), savedTransaction.getMaturity_ins());

        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void getAllLoginTest() {
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction(1, "1234567890", "9876543210", "Transfer", "myPassword123", new Date(), 1000L, "Payment for services", "2023-12-31");
    	Transaction transaction2 = new Transaction(2, "1234567590", "98765432101", "Transfer", "myPassword123", new Date(), 10000L, "Payment for services", "2023-12-31");
    	Transaction transaction3 = new Transaction(3, "1234567690", "9666543210", "Transfer", "myPassword123", new Date(), 2000L, "Payment for services", "2023-12-31");
        transactions = Arrays.asList(transaction1 ,transaction2, transaction3);
        
        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> retrievedTransactions = transactionService.getAllLogin();

        assertEquals(transactions.size(), retrievedTransactions.size());
        for (int i = 0; i < transactions.size(); i++) {
            Transaction retrievedTransaction = retrievedTransactions.get(i);
            Transaction transaction = transactions.get(i);
            assertNotNull(retrievedTransaction);
            assertEquals(transaction.getTrans_id(), retrievedTransaction.getTrans_id());
            assertEquals(transaction.getSend_acc(), retrievedTransaction.getSend_acc());
            assertEquals(transaction.getRec_acc(), retrievedTransaction.getRec_acc());
            assertEquals(transaction.getTrans_type(), retrievedTransaction.getTrans_type());
            assertEquals(transaction.getTrans_pass(), retrievedTransaction.getTrans_pass());
            assertEquals(transaction.getDate(), retrievedTransaction.getDate());
            assertEquals(transaction.getAmount(), retrievedTransaction.getAmount());
            assertEquals(transaction.getRemarks(), retrievedTransaction.getRemarks());
            assertEquals(transaction.getMaturity_ins(), retrievedTransaction.getMaturity_ins());
        }
        
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    public void getByAccTest() {
    	List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction(1, "1234567890", "9876543210", "Transfer", "myPassword123", new Date(), 1000L, "Payment for services", "2023-12-31");
     	Transaction transaction2 = new Transaction(2, "1234567590", "98765432101", "Transfer", "myPassword123", new Date(), 10000L, "Payment for services", "2023-12-31");
     	Transaction transaction3 = new Transaction(3, "1234567690", "9666543210", "Transfer", "myPassword123", new Date(), 2000L, "Payment for services", "2023-12-31");
        transactions = Arrays.asList(transaction1 ,transaction2, transaction3);
        int accountId = 1;
        when(transactionRepository.getAccountTrans(accountId)).thenReturn(Optional.of(transactions));

        Optional<List<Transaction>> retrievedTransactions = transactionService.getByAcc(accountId);

        assertTrue(retrievedTransactions.isPresent());
        assertEquals(transactions.size(), retrievedTransactions.get().size());
        
        verify(transactionRepository, times(1)).getAccountTrans(accountId);
    }

    @Test
    public void removeUserTest() {
        Transaction transaction = new Transaction(1, "1234567890", "9876543210", "Transfer", "myPassword123", new Date(), 1000L, "Payment for services", "2023-12-31");

        transactionService.remove_user(transaction);

        verify(transactionRepository, times(1)).delete(transaction);
    }
}
