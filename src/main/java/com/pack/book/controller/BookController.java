package com.pack.book.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.book.dto.BookDTO;
import com.pack.book.service.BookService;
import com.pack.book.serviceimpl.BookServiceimpl;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookServiceimpl.class);

	@Autowired
	private BookService bookService;

	@PostMapping
	public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
		BookDTO book = this.bookService.addBook(bookDTO);
		return ResponseEntity.ok(book);
	}

	@GetMapping
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		List<BookDTO> allBooks = bookService.getAllBooks();
		if (allBooks.isEmpty()) {
			logger.info("No books found to return in the response.");
		} else {
			logger.info("Returning {} books.", allBooks.size());
		}
		return ResponseEntity.status(HttpStatus.OK).body(allBooks);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
		BookDTO book = this.bookService.getBookById(id);
		return ResponseEntity.status(HttpStatus.OK).body(book);
	}
}
