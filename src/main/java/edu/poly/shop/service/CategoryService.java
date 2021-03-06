package edu.poly.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.shop.domain.Category;

public interface CategoryService {

	Category getById(Long id);

	void deleteAll();

	void delete(Category entity);

	void deleteById(Long id);

	long count();

	<S extends Category> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Category> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	Optional<Category> findById(Long id);

	List<Category> findAllById(Iterable<Long> ids);

	List<Category> findAll(Sort sort);

	Page<Category> findAll(Pageable pageable);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	List<Category> findByNameContaining(String name);

	Page<Category> findByNameContaining(String name, Pageable page);

}
