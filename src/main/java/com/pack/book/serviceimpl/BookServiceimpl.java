package com.pack.book.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.book.dto.AuthorDTO;
import com.pack.book.dto.BookDTO;
import com.pack.book.dto.CategoryDTO;
import com.pack.book.dto.PublisherDTO;
import com.pack.book.model.Author;
import com.pack.book.model.Book;
import com.pack.book.model.Category;
import com.pack.book.model.Publisher;
import com.pack.book.repository.AuthorRepository;
import com.pack.book.repository.BookRepository;
import com.pack.book.repository.CategoryReository;
import com.pack.book.repository.PublisherRepository;
import com.pack.book.service.BookService;

@Service
public class BookServiceimpl implements BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookServiceimpl.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private CategoryReository categoryRepository;

	@Override
	public BookDTO addBook(BookDTO bookDTO) {
		Author author = dtoToEntity(bookDTO.getAuthorDTO());
		Author savedAuthor = authorRepository.save(author);

		Publisher publisher = dtoToEntity(bookDTO.getPublisherDTO());
		Publisher savedPublisher = publisherRepository.save(publisher);

		List<Category> savedCategories = bookDTO.getCategoryDTOs().stream().map(this::dtoToEntity)
				.map(categoryRepository::save).collect(Collectors.toList());

		Book book = dtoToEntity(bookDTO);
		book.setAuthor(savedAuthor);
		book.setPublisher(savedPublisher);
		book.setCategories(savedCategories);

		Book savedBook = bookRepository.save(book);
		return entityToDTO(savedBook);
	}
	

	/*
	 * @Override public List<BookDTO> getAllBooks() { return
	 * bookRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.
	 * toList()); }
	 */

	@Override
	public List<BookDTO> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		if (books.isEmpty()) {
			logger.info("No books found in the database.");
		} else {
			logger.info("Books found: {}", books.size());
		}
		return books.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	@Override
	public BookDTO getBookById(Long id) {
		Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
		return entityToDTO(book);
	}

	@Override
	public BookDTO updateBook(Long id, BookDTO bookDTO) {
		Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
		modelMapper.map(bookDTO, existingBook);
		Book updatedBook = bookRepository.save(existingBook);
		return entityToDTO(updatedBook);
	}

	@Override
	public void deleteBook(Long id) {
		if (bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("Book not found");
		}
	}

	// convert dto to entity
//	public Book dtoToEntity(BookDTO bookDTO) {
//		return modelMapper.map(bookDTO, Book.class);
//	}

	private Book dtoToEntity(BookDTO bookDTO) {
		Book book = modelMapper.map(bookDTO, Book.class);
		if (bookDTO.getAuthorDTO() != null) {
			book.setAuthor(dtoToEntity(bookDTO.getAuthorDTO()));
		}
		if (bookDTO.getPublisherDTO() != null) {
			book.setPublisher(dtoToEntity(bookDTO.getPublisherDTO()));
		}
		if (bookDTO.getCategoryDTOs() != null) {
			List<Category> categories = bookDTO.getCategoryDTOs().stream().map(this::dtoToEntity)
					.collect(Collectors.toList());
			book.setCategories(categories);
		}
		return book;
	}

	// convert entity to dto
//	public BookDTO entityToDTO(Book book) {
//		return modelMapper.map(book, BookDTO.class);
//	}

	/*
	 * private BookDTO entityToDTO(Book book) { BookDTO bookDTO =
	 * modelMapper.map(book, BookDTO.class); if (book.getAuthor() != null) {
	 * bookDTO.setAuthorDTO(modelMapper.map(book.getAuthor(), AuthorDTO.class)); }
	 * if (book.getPublisher() != null) {
	 * bookDTO.setPublisherDTO(modelMapper.map(book.getPublisher(),
	 * PublisherDTO.class)); } if (book.getCategories() != null) { Set<CategoryDTO>
	 * categoryDTOs = book.getCategories().stream() .map(category ->
	 * modelMapper.map(category, CategoryDTO.class)) .collect(Collectors.toSet());
	 * bookDTO.setCategoryDTOs(categoryDTOs);
	 * logger.info("Converted categories for book {}: {}", book.getTitle(),
	 * categoryDTOs); } else { logger.info("No categories found for book {}",
	 * book.getTitle()); } return bookDTO; }
	 */

	private BookDTO entityToDTO(Book book) {
		BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
		if (book.getAuthor() != null) {
			bookDTO.setAuthorDTO(modelMapper.map(book.getAuthor(), AuthorDTO.class));
		}
		if (book.getPublisher() != null) {
			bookDTO.setPublisherDTO(modelMapper.map(book.getPublisher(), PublisherDTO.class));
		}
		if (book.getCategories() != null && !book.getCategories().isEmpty()) {
			List<CategoryDTO> categoryDTOs = book.getCategories().stream()
					.map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
			bookDTO.setCategoryDTOs(categoryDTOs);
		} else {
			bookDTO.setCategoryDTOs(Collections.emptyList());
		}
		return bookDTO;
	}

	// Convert AuthorDTO to Author entity
	private Author dtoToEntity(AuthorDTO authorDTO) {
		return modelMapper.map(authorDTO, Author.class);
	}

	// Convert PublisherDTO to Publisher entity
	private Publisher dtoToEntity(PublisherDTO publisherDTO) {
		return modelMapper.map(publisherDTO, Publisher.class);
	}

	// Convert CategoryDTO to Category entity
	private Category dtoToEntity(CategoryDTO categoryDTO) {
		return modelMapper.map(categoryDTO, Category.class);
	}
}
