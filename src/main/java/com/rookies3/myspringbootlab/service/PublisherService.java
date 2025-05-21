package com.rookies3.myspringbootlab.service;

import com.rookies3.myspringbootlab.controller.dto.PublisherDTO;
import com.rookies3.myspringbootlab.entity.Publisher;
import com.rookies3.myspringbootlab.exception.BusinessException;
import com.rookies3.myspringbootlab.exception.ErrorCode;
import com.rookies3.myspringbootlab.repository.BookRepository;
import com.rookies3.myspringbootlab.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    //    getAllPublishers()
    public List<PublisherDTO.SimpleResponse> findAll() {
        return publisherRepository.findAll().stream().map(PublisherDTO.SimpleResponse::fromEntity).toList();
    }

    //    getPublisherById(Long id)
    public PublisherDTO.SimpleResponse getPublisherByID(Long id) {
        Publisher publisher = publisherRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher with id " + id + " not found"));
        return PublisherDTO.SimpleResponse.fromEntity(publisher);
    }

    //    getPublisherByName(String name)
    public PublisherDTO.SimpleResponse getPublisherByName(String name) {
        Publisher publisher = publisherRepository
                .findByName(name).orElseThrow(() -> new RuntimeException("Publisher with id \" + id + \" not found"));
        return PublisherDTO.SimpleResponse.fromEntity(publisher);
    }

    //    createPublisher(PublisherDTO.Request request)
    @Transactional
    public PublisherDTO.SimpleResponse createPublisher(PublisherDTO.Request request) {
        Publisher publisher = Publisher.builder()
                .name(request.getName())
                .address(request.getAddress())
                .establishedDate(request.getEstablishedDate())
                .build();
        return PublisherDTO.SimpleResponse.fromEntity(publisherRepository.save(publisher));
    }

    //    updatePublisher(Long id, PublisherDTO.Request request)
    @Transactional
    public PublisherDTO.SimpleResponse updatePublisher(Long id, PublisherDTO.Request request) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher with id " + id + " not found"));

        publisher.setName(request.getName());
        publisher.setAddress(request.getAddress());
        publisher.setEstablishedDate(request.getEstablishedDate());

        return PublisherDTO.SimpleResponse.fromEntity(publisher);
    }

    //    deletePublisher(Long id)
    @Transactional
    public void deletePublisher(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher with id " + id + " not found"));
        publisherRepository.delete(publisher);
    }


}
