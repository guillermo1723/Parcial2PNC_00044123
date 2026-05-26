package org.example.ejercicio2parcial.service;


import org.example.ejercicio2parcial.dto.BookRequestDTO;
import org.example.ejercicio2parcial.dto.BookResponseDTO;
import org.example.ejercicio2parcial.entity.Book;
import org.example.ejercicio2parcial.exception.DuplicateIsbnException;
import org.example.ejercicio2parcial.exception.ResourceNotFoundException;
import org.example.ejercicio2parcial.repository.BookRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    // CREAR LIBRO
    @Override
    public BookResponseDTO createBook(BookRequestDTO requestDTO) {

        if (bookRepository.existsByIsbn(requestDTO.getIsbn())) {
            throw new DuplicateIsbnException("El ISBN ya existe");
        }

        Book book = mapToEntity(requestDTO);

        Book savedBook = bookRepository.save(book);

        return mapToResponseDTO(savedBook);
    }

    // OBTENER TODOS
    @Override
    public List<BookResponseDTO> getAllBooks() {

        return bookRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // OBTENER POR ID
    @Override
    public BookResponseDTO getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Libro no encontrado con id: " + id
                        ));

        return mapToResponseDTO(book);
    }

    // ACTUALIZAR
    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO requestDTO) {

        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Libro no encontrado con id: " + id
                        ));

        // VALIDAR ISBN DUPLICADO
        if (!existingBook.getIsbn().equals(requestDTO.getIsbn())
                && bookRepository.existsByIsbn(requestDTO.getIsbn())) {

            throw new DuplicateIsbnException("El ISBN ya existe");
        }

        existingBook.setTitle(requestDTO.getTitle());
        existingBook.setAuthor(requestDTO.getAuthor());
        existingBook.setIsbn(requestDTO.getIsbn());
        existingBook.setPublicationYear(requestDTO.getPublicationYear());
        existingBook.setLanguage(requestDTO.getLanguage());
        existingBook.setPages(requestDTO.getPages());

        Book updatedBook = bookRepository.save(existingBook);

        return mapToResponseDTO(updatedBook);
    }

    // ELIMINAR
    @Override
    public void deleteBook(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Libro no encontrado con id: " + id
                        ));

        bookRepository.delete(book);
    }

    // FILTRAR POR AUTOR
    @Override
    public List<BookResponseDTO> filterByAuthor(String author) {

        return bookRepository.findByAuthorContainingIgnoreCase(author)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // FILTRAR POR IDIOMA
    @Override
    public List<BookResponseDTO> filterByLanguage(String language) {

        return bookRepository.findByLanguageIgnoreCase(language)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // FILTRAR POR RANGO DE PAGINAS
    @Override
    public List<BookResponseDTO> filterByPages(
            Integer minPages,
            Integer maxPages
    ) {

        return bookRepository.findByPagesBetween(minPages, maxPages)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // =========================
    // MÉTODOS PRIVADOS
    // =========================

    private Book mapToEntity(BookRequestDTO dto) {

        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .isbn(dto.getIsbn())
                .publicationYear(dto.getPublicationYear())
                .language(dto.getLanguage())
                .pages(dto.getPages())
                .build();
    }

    private BookResponseDTO mapToResponseDTO(Book book) {

        return BookResponseDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear())
                .language(book.getLanguage())
                .pages(book.getPages())
                .build();
    }
}