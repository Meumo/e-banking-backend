package sn.meum.ebankingbackend.services;

import sn.meum.ebankingbackend.dtos.*;
import sn.meum.ebankingbackend.exceptions.BalanceNotSufficientException;
import sn.meum.ebankingbackend.exceptions.BankAccountNotFoundException;
import sn.meum.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) throws CustomerNotFoundException;

    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, Long customerId, double interestRate) throws CustomerNotFoundException;

    List<CustomerDTO> listCustomers();

    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;

    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;

    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccountDTO> bankAccountList();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    List<CustomerDTO> searchCustomers(String keyword);

    List<BankAccountDTO> getAccountsByCustomer(Long customerId);
}
