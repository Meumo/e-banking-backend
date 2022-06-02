package sn.meum.ebankingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.meum.ebankingbackend.entities.BankAccount;
import sn.meum.ebankingbackend.entities.Customer;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
