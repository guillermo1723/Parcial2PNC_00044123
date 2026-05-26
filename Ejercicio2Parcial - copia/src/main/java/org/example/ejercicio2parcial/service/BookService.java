package org.example.ejercicio2parcial.service;


import org.example.ejercicio2parcial.dto.BookRequestDTO;
import org.example.ejercicio2parcial.dto.BookResponseDTO;

import java.util.List;

public interface BookService {

    BookResponseDTO createBook(BookRequestDTO requestDTO);

    List<BookResponseDTO> getAllBooks();

    BookResponseDTO getBookById(Long id);

    BookResponseDTO updateBook(Long id, BookRequestDTO requestDTO);

    void deleteBook(Long id);

    List<BookResponseDTO> filterByAuthor(String author);

    List<BookResponseDTO> filterByLanguage(String language);

    List<BookResponseDTO> filterByPages(Integer minPages, Integer maxPages);
}