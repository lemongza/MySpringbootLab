package com.rookies3.myspringbootlab.repository;

import com.rookies3.myspringbootlab.entity.Book;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    //도서 등록 테스트
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

    //ISBN 으로 도서 조회 테스트
    @Test
    void testFindByIsbn() {
        Optional<Book> optionalBook = bookRepository.findByIsbn("9788956746432");
        if (optionalBook.isPresent()){
            Book book = optionalBook.get();
            System.out.println(book.getTitle());
            assertThat(book.getIsbn()).isEqualTo("9788956746432");
        }
    }

    //저자명으로 도서 목록 조회 테스트
    @Test
    @Rollback(value = false)
    void testFindByAuthor() {
        List<Book> books = bookRepository.findByAuthor("홍길동");
        if (!books.isEmpty()){
            Book book = books.get(0);
            System.out.println(books.get(0).getTitle());
            assertThat(book.getAuthor()).isEqualTo("홍길동");
        }

    }

    //도서 정보 수정 테스트
    @Test
    @Rollback(value = false)
    void testUpdateBook() {
        Book book = bookRepository.findByIsbn("9788956746432")
            .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setPrice(40000);
        bookRepository.save(book);
        assertThat(book.getPrice()).isEqualTo(40000);
    }


    //도서 삭제 테스트
    @Test
    @Rollback(value = false)
    void testDeleteBook() {
        Book book =bookRepository.findByIsbn("9788956746425")
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }
}