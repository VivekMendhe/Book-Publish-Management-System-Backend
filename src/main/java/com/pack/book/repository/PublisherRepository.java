package com.pack.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.book.model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
