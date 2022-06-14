package sn.meum.ebankingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.meum.ebankingbackend.entities.AccountOperation;
import sn.meum.ebankingbackend.entities.BankAccount;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    List<BankAccount> findByCustomerId(Long customerId);
}
