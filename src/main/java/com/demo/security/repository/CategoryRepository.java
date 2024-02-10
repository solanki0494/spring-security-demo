package com.demo.security.repository;

import com.demo.security.domain.Category;
import com.demo.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    List<Category> findByNameOrDescription(String name, String description);

    List<Category> findByNameContaining(String keyword);




}
