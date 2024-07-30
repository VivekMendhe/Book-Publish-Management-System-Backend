package com.pack.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.book.model.Category;

@Repository
public interface CategoryReository extends JpaRepository<Category, Long>{

}
