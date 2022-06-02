package sn.meum.ebankingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.meum.ebankingbackend.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
