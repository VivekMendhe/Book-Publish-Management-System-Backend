package com.pack.book.dto;

import java.util.List;

import com.pack.book.model.Book;

import lombok.Data;

@Data
public class AuthorDTO {
	private Long id;
	private String name;
//	private List<Book> books;
}
