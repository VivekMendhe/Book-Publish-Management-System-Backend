package com.pack.book.service;

import java.util.List;

import com.pack.book.dto.BookDTO;

public interface BookService {

	BookDTO addBook(BookDTO bookDTO);

	List<BookDTO> getAllBooks();

	BookDTO getBookById(Long id);

	BookDTO updateBook(Long id, BookDTO bookDTO);

	void deleteBook(Long id);
}
