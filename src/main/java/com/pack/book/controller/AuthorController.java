package com.pack.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.book.dto.AuthorDTO;
import com.pack.book.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@PostMapping
	public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
		AuthorDTO addAuthor = this.authorService.addAuthor(authorDTO);
		return ResponseEntity.status(201).body(addAuthor);
	}
 
	@GetMapping
	public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
		List<AuthorDTO> allAuthors = this.authorService.getAllAuthors();
		return ResponseEntity.status(201).body(allAuthors);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
		AuthorDTO authorById = this.authorService.getAuthorById(id);
		return ResponseEntity.status(201).body(authorById);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
		AuthorDTO updateAuthor = this.authorService.updateAuthor(id, authorDTO);
		return ResponseEntity.status(201).body(updateAuthor);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
		this.authorService.deleteAuthor(id);
		return ResponseEntity.status(201).body("Author delete successully");
	}
}
