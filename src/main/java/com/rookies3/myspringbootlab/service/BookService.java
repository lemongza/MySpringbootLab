package com.rookies3.myspringbootlab.service;


import com.rookies3.myspringbootlab.controller.dto.BookDTO;
import com.rookies3.myspringbootlab.entity.Book;
import com.rookies3.myspringbootlab.exception.BusinessException;
import com.rookies3.myspringbootlab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDTO.BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDTO.BookResponse::from)
                .toList();
    }


    public BookDTO.BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book not found"));
        return BookDTO.BookResponse.from(book);
    }


    public BookDTO.BookResponse getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException("Book not found"));
        return BookDTO.BookResponse.from(book);
    }

    public List<BookDTO.BookResponse> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author)
                .stream()
                .map(BookDTO.BookResponse::from)
                .collect(Collectors.toList());
    }

    public BookDTO.BookResponse createBook(BookDTO.BookCreateRequest request) {
        Book book = request.toEntity();
        Book savedbook = bookRepository.save(book);
        return BookDTO.BookResponse.from(savedbook);
    }

    public BookDTO.BookResponse updateBook(Long id, BookDTO.BookUpdateRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book not found"));
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        return BookDTO.BookResponse.from(bookRepository.save(book));

    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
