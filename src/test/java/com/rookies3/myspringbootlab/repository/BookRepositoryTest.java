package com.rookies3.myspringbootlab.repository;

import com.rookies3.myspringbootlab.entity.Book;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    @Rollback(value = false)
    void testCreateBook() {
        Book book = new Book();
        Book book2 = new Book();

        book.setTitle("스프링 부트 입문");
        book.setAuthor("홍길동");
        book.setIsbn("9788956746425");
        book.setPrice(30000);
        book.setPublishDate(LocalDate.of(2025, 5, 7));

        book2.setTitle("JPA 프로그래밍");
        book2.setAuthor("박둘리");
        book2.setIsbn("9788956746432");
        book2.setPrice(35000);
        book2.setPublishDate(LocalDate.of(2025, 4, 30));

        Book addBook = bookRepository.save(book);
        Book addBook2 = bookRepository.save(book2);

        assertThat(addBook).isNotNull();
        assertThat(addBook.getTitle()).isEqualTo("스프링 부트 입문");

        assertThat(addBook2).isNotNull();
        assertThat(addBook2.getTitle()).isEqualTo("JPA 프로그래밍");
    }



    @Test
    void testFindByIsbn() {
    }

    @Test
    void testFindByAuthor() {
    }

    @Test
    void testUpdateBook() {
    }

    @Test
    void testDeleteBook() {
    }
}