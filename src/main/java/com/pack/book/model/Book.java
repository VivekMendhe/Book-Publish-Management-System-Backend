package com.pack.book.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;

	@ManyToOne // author
	@JoinColumn(name = "author_id")
	private Author author;

	@ManyToOne // publisher
	@JoinColumn(name = "publisher_id")
	private Publisher publisher;

//	@ManyToMany // Catergory
//	@JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
//	private Set<Category> categories;
	
	@ManyToMany(fetch = FetchType.EAGER) // Change to EAGER fetching
	@JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;

	
}
