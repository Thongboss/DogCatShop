package edu.poly.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query("select p from products p where p.status = ?1")
	List<Product> findByStatus(int status);
}
