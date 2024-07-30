package com.pack.book.dto;

import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class BookDTO {
	private Long id;
	private String title;
	private AuthorDTO authorDTO;
	private PublisherDTO publisherDTO;
	private List<CategoryDTO> categoryDTOs;
}
