package com.pack.book.serviceimpl;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.book.dto.AuthorDTO;
import com.pack.book.model.Author;
import com.pack.book.repository.AuthorRepository;
import com.pack.book.service.AuthorService;

@Service
public class AuthorServiceimpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AuthorDTO addAuthor(AuthorDTO authorDTO) {
		Author author = this.dtoToEntity(authorDTO);
		Author saveAuthor = this.authorRepository.save(author);
		return this.entityToDTO(saveAuthor);
	}

	@Override
	public List<AuthorDTO> getAllAuthors() {
		return authorRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	@Override
	public AuthorDTO getAuthorById(Long id) {
		Author author = this.authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author Not Found!"));
		return entityToDTO(author);
	}

	@Override
	public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
		Author exsitingAuthor = this.authorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Author not found!"));
		modelMapper.map(authorDTO, exsitingAuthor);
		Author saveAuthor = authorRepository.save(exsitingAuthor);
		return entityToDTO(saveAuthor);
	}

	@Override
	public void deleteAuthor(Long id) {
		if (this.authorRepository.existsById(id)) {
			authorRepository.deleteById(id);
		} else {
			throw new ResolutionException("Author not found!");
		}
	}

	// convert dto to entity
	public Author dtoToEntity(AuthorDTO authorDTO) {
		return modelMapper.map(authorDTO, Author.class);
	}

	// convert entity to dto
	public AuthorDTO entityToDTO(Author author) {
		return modelMapper.map(author, AuthorDTO.class);
	}

}
