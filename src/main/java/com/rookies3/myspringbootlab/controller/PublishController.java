package com.rookies3.myspringbootlab.controller;

import com.rookies3.myspringbootlab.controller.dto.PublisherDTO;
import com.rookies3.myspringbootlab.controller.dto.BookDTO;
import com.rookies3.myspringbootlab.entity.Book;
import com.rookies3.myspringbootlab.entity.BookDetail;
import com.rookies3.myspringbootlab.entity.Publisher;
import com.rookies3.myspringbootlab.repository.PublisherRepository;
import com.rookies3.myspringbootlab.service.BookService;
import com.rookies3.myspringbootlab.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublishController {
    private final PublisherService publisherService;
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<PublisherDTO.SimpleResponse>> findAll(){
        List<PublisherDTO.SimpleResponse> publishers = publisherService.findAll();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO.SimpleResponse> getPublisherByID(@PathVariable Long id) {
       PublisherDTO.SimpleResponse publisher = publisherService.getPublisherByID(id);
       return ResponseEntity.ok(publisher);
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<PublisherDTO.SimpleResponse> getPublisherByName(@PathVariable String name) {
        PublisherDTO.SimpleResponse publisher = publisherService.getPublisherByName(name);
        return ResponseEntity.ok(publisher);
    }



    @PostMapping
    public ResponseEntity<PublisherDTO.SimpleResponse> createPublisher(@Valid @RequestBody PublisherDTO.Request request) {
        PublisherDTO.SimpleResponse createdPublisher = publisherService.createPublisher(request);
        return new ResponseEntity<>(createdPublisher,HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDTO.SimpleResponse> upDatePublisher(
            @PathVariable Long id,
            @Valid @RequestBody PublisherDTO.Request request) {
        PublisherDTO.SimpleResponse updatedPublisher = publisherService.updatePublisher(id, request);
        return ResponseEntity.ok(updatedPublisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }


}
