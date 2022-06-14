package sn.meum.ebankingbackend.web;

import org.springframework.web.bind.annotation.*;
import sn.meum.ebankingbackend.dtos.*;
import sn.meum.ebankingbackend.exceptions.BalanceNotSufficientException;
import sn.meum.ebankingbackend.exceptions.BankAccountNotFoundException;
import sn.meum.ebankingbackend.exceptions.CustomerNotFoundException;
import sn.meum.ebankingbackend.services.BankAccountService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BankAccountRestController {
    private BankAccountService bankAccountService;

    public BankAccountRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable(name = "accountId") String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> listBankAccount() {
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId) {
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId, page, size);
    }

    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
        bankAccountService.credit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());
        return creditDTO;
    }

    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
        return debitDTO;
    }

    @PostMapping("/accounts/transfer")
    public TransferRequestDTO transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.transfer(transferRequestDTO.getAccountSource(), transferRequestDTO.getAccountDestination(), transferRequestDTO.getAmount());
        return transferRequestDTO;
    }

    @GetMapping("/accounts/customer/{customerId}")
    public List<BankAccountDTO> getBankAccountsByCustomer(@PathVariable Long customerId) {
        return bankAccountService.getAccountsByCustomer(customerId);
    }

    @PostMapping("/accounts/savingAccount/{customerId}")
    public SavingBankAccountDTO saveSavingBankAccount(@RequestBody SavingBankAccountDTO savingBankAccountDTO,@PathVariable Long customerId) throws CustomerNotFoundException {
        return bankAccountService.saveSavingBankAccount(savingBankAccountDTO.getBalance(), customerId, savingBankAccountDTO.getInterestRate());
    }

    @PostMapping("/accounts/currentAccount/{customerId}")
    public CurrentBankAccountDTO saveCurrentBankAccount(@RequestBody CurrentBankAccountDTO  currentBankAccountDTO,@PathVariable Long customerId) throws CustomerNotFoundException {
        return bankAccountService.saveCurrentBankAccount(currentBankAccountDTO.getBalance(),customerId,currentBankAccountDTO.getOverDraft());
    }
}
