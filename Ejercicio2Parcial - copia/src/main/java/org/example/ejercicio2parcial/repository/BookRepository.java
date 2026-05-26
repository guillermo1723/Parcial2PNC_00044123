package org.example.ejercicio2parcial.repository;


import org.example.ejercicio2parcial.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbn(String isbn);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByLanguageIgnoreCase(String language);

    List<Book> findByPagesBetween(Integer minPages, Integer maxPages);
}