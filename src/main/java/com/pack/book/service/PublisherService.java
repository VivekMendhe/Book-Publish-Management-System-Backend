package com.pack.book.service;

import java.util.List;

import com.pack.book.dto.PublisherDTO;

public interface PublisherService {
	PublisherDTO addPublisher(PublisherDTO publisherDTO);

	List<PublisherDTO> getAllPublishers();

	PublisherDTO getPublisherById(Long id);

	PublisherDTO updatePublisher(Long id, PublisherDTO publisherDTO);

	void deletePublisher(Long id);
}
