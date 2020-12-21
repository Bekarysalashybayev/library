package com.library.automationsystem.book.controller;

import com.library.automationsystem.book.model.Book;
import com.library.automationsystem.book.model.Publisher;
import com.library.automationsystem.book.repository.BookRepository;
import com.library.automationsystem.book.repository.GenreRepository;
import com.library.automationsystem.book.repository.PublisherRepository;
import com.library.automationsystem.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class BookController {
    @Autowired
    GenreRepository genreRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/getBookById/{id}")
    public Optional<Book> getBookById(@PathVariable(value = "id") Long id) {
        return bookRepository.findById(id);
    }

    @GetMapping("/bookByGenreId/{genreId}")
    public List<Book> getAllBooksByGenreId(@PathVariable(value = "genreId") Long genreId) {
        return bookRepository.findByGenreId(genreId);
    }

    @GetMapping("/bookByAuthorId/{authorId}")
    public List<Book> getAllBooksByAuthorId(@PathVariable(value = "authorId") Long authorId) {
        return bookRepository.findByPublisherId(authorId);
    }

    @GetMapping("/bookByAuthorIdAndGenreId/author/{authorId}/genre/{genreId}")
    public List<Book> getAllBooksByAuthorIdAndGenreId(@PathVariable(value = "authorId") Long authorId,
                                                      @PathVariable(value = "genreId") Long genreId) {
        return bookRepository.findByPublisherIdAndGenreId(authorId, genreId);
    }

    @PostMapping("/addBook/genre/{genreId}/author/{authorId}")
    public Book addBook(@PathVariable(value = "genreId") Long genreId,
                        @PathVariable(value = "authorId") Long authorId,
                        @RequestBody Book book) {

        return genreRepository.findById(genreId).map(genre -> {
            Optional<Publisher> author = publisherRepository.findById(authorId);
            if (author.isEmpty()) {
                book.setBook_name("Author id not found");
                return book;
            }
            book.setPublisher(author.get());
            book.setGenre(genre);
            return bookRepository.save(book);
        }).orElseThrow(() -> new ResourceNotFoundException("Genre Id " + genreId + " not found"));
    }

    @PutMapping("/updateBook/{id}")
    public Book updateBook(@PathVariable(value = "id") Long id, @RequestBody Book bookDetails) {
        Optional<Book> book = bookRepository.findById(id);
        Book book_new = book.get();
        book_new.setBook_name(bookDetails.getBook_name());
        return bookRepository.save(book_new);

    }

    @DeleteMapping("/deleteBook/{id}")
    public boolean deleteBook(@PathVariable(value = "id") Long id) {
        Optional<Book> book = bookRepository.findById(id);
        Book book_new = book.get();
        bookRepository.delete(book_new);
        return true;
    }
}
