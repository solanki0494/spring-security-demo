package com.demo.security.repository;

import com.demo.security.domain.Category;
import com.demo.security.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
    List<Product> findByPrice(double price);
    List<Product> findByCategory(Category category);

    List<Product> findByCategoryAndPriceLessThan(Category category, double price);

    List<Product> findByCategoryOrPriceGreaterThan(Category category, double price);

    List<Product> findByNameContaining(String keyword);




}
