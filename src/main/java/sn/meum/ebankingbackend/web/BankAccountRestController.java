package sn.meum.ebankingbackend.web;

import org.springframework.web.bind.annotation.*;
import sn.meum.ebankingbackend.dtos.AccountHistoryDTO;
import sn.meum.ebankingbackend.dtos.AccountOperationDTO;
import sn.meum.ebankingbackend.dtos.BankAccountDTO;
import sn.meum.ebankingbackend.exceptions.BalanceNotSufficientException;
import sn.meum.ebankingbackend.exceptions.BankAccountNotFoundException;
import sn.meum.ebankingbackend.services.BankAccountService;

import java.util.List;

@RestController
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

    @PostMapping("/accounts/{accountId}/credit")
    public void credit(@PathVariable String accountId, double amount, String description) throws BankAccountNotFoundException {
        bankAccountService.credit(accountId, amount, description);
    }

    @PostMapping("/accounts/{accountId}/debit")
    public void debit(@PathVariable String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.debit(accountId, amount, description);
    }

    @PostMapping("/accounts/{accountIdSource}/{accountIdDestination}/transfer")
    public void transfer(@PathVariable String accountIdSource, @PathVariable String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.transfer(accountIdSource, accountIdDestination, amount);
    }
}
