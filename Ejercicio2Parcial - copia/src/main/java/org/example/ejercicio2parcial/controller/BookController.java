package org.example.ejercicio2parcial.controller;

import org.example.ejercicio2parcial.dto.BookRequestDTO;
import org.example.ejercicio2parcial.dto.BookResponseDTO;
import org.example.ejercicio2parcial.service.BookService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // CREAR LIBRO
    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(
            @Valid @RequestBody BookRequestDTO requestDTO
    ) {

        BookResponseDTO response = bookService.createBook(requestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // OBTENER TODOS LOS LIBROS
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(

            @RequestParam(required = false) String author,

            @RequestParam(required = false) String language,

            @RequestParam(required = false) Integer minPages,

            @RequestParam(required = false) Integer maxPages
    ) {

        // FILTRO POR AUTOR
        if (author != null) {
            return ResponseEntity.ok(
                    bookService.filterByAuthor(author)
            );
        }

        // FILTRO POR IDIOMA
        if (language != null) {
            return ResponseEntity.ok(
                    bookService.filterByLanguage(language)
            );
        }

        // FILTRO POR RANGO DE PAGINAS
        if (minPages != null && maxPages != null) {
            return ResponseEntity.ok(
                    bookService.filterByPages(minPages, maxPages)
            );
        }

        return ResponseEntity.ok(
                bookService.getAllBooks()
        );
    }

    // OBTENER LIBRO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                bookService.getBookById(id)
        );
    }

    // ACTUALIZAR LIBRO
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookRequestDTO requestDTO
    ) {

        return ResponseEntity.ok(
                bookService.updateBook(id, requestDTO)
        );
    }

    // ELIMINAR LIBRO
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(
            @PathVariable Long id
    ) {

        bookService.deleteBook(id);

        return ResponseEntity.ok(
                "Libro eliminado correctamente"
        );
    }
}