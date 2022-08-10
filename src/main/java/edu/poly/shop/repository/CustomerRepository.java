package edu.poly.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query(value = "SELECT * FROM customers where email = ?1",nativeQuery = true)
	Customer findByEmail(String email);
}
