package sn.meum.ebankingbackend.dtos;

import lombok.Data;
import sn.meum.ebankingbackend.entities.AccountOperation;
import sn.meum.ebankingbackend.entities.Customer;
import sn.meum.ebankingbackend.enums.AccountStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdDate;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double interestRate;
}
