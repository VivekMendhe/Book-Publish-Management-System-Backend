package com.pack.book.serviceimpl;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.book.dto.PublisherDTO;
import com.pack.book.model.Publisher;
import com.pack.book.repository.PublisherRepository;
import com.pack.book.service.PublisherService;

@Service
public class PublisherServiceimpl implements PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PublisherDTO addPublisher(PublisherDTO publisherDTO) {
		Publisher publisher = this.dtoToEntity(publisherDTO);
		Publisher savePublisher = publisherRepository.save(publisher);
		return this.entityToDTO(savePublisher);
	}

	@Override
	public List<PublisherDTO> getAllPublishers() {
		return publisherRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	@Override
	public PublisherDTO getPublisherById(Long id) {
		Publisher publisher = publisherRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Publisher not found"));
		return entityToDTO(publisher);
	}

	@Override
	public PublisherDTO updatePublisher(Long id, PublisherDTO publisherDTO) {
		Publisher existedPublisher = publisherRepository.findById(id).orElseThrow(()-> new RuntimeException("Publisher not found"));
		modelMapper.map(publisherDTO, existedPublisher);
		Publisher savePublisher = publisherRepository.save(existedPublisher);
		return entityToDTO(savePublisher);
	}

	@Override
	public void deletePublisher(Long id) {
		if (publisherRepository.existsById(id)) {
			publisherRepository.deleteById(id);
		} else {
			throw new ResolutionException("Publisher id not found");
		}
	}

	// convert dto to entity
	public Publisher dtoToEntity(PublisherDTO publisherDTO) {
		return modelMapper.map(publisherDTO, Publisher.class);
	}

	// convert entity to dto
	public PublisherDTO entityToDTO(Publisher publisher) {
		return modelMapper.map(publisher, PublisherDTO.class);
	}
	
	

}
