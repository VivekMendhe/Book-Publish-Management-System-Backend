package com.pack.book.service;

import java.util.List;

import com.pack.book.dto.AuthorDTO;

public interface AuthorService {

	AuthorDTO addAuthor(AuthorDTO authorDTO);

	List<AuthorDTO> getAllAuthors();

	AuthorDTO getAuthorById(Long id);

	AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);

	void deleteAuthor(Long id);
	
}
