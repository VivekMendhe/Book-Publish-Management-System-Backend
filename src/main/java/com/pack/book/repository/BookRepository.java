package com.pack.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.book.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	@EntityGraph(attributePaths = {"categories"})
    List<Book> findAll();
}
