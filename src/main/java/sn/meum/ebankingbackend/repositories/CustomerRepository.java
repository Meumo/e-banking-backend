package sn.meum.ebankingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sn.meum.ebankingbackend.entities.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("select c from Customer c where c.name like :kw")
    List<Customer> searchCustomers(@Param("kw") String keyword);
    // Ou
    List<Customer> findByNameContains(String keyword);
}
