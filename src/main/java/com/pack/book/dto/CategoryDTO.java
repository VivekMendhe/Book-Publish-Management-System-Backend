package com.pack.book.dto;

import java.util.Set;

import com.pack.book.model.Book;

import lombok.Data;

@Data
public class CategoryDTO {

	private Long id;
	private String name;
//	private Set<Book> books;
}
