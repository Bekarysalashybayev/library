package com.library.automationsystem.book.controller;

import com.library.automationsystem.book.model.Book;
import com.library.automationsystem.book.model.Genre;
import com.library.automationsystem.book.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class GenreController {
    @Autowired
    GenreRepository genreRepository;

    @GetMapping("/getAllGenres")
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @GetMapping("/getGenreById/{id}")
    public Optional<Genre> getGenreById(@PathVariable(value = "id") Long id) {
        return genreRepository.findById(id);
    }

    @PostMapping("/addGenre")
    public Genre addGenre(@RequestBody Genre genre) {
        return genreRepository.save(genre);
    }

    @PutMapping("/updateGenre/{id}")
    public Genre updateGenre(@PathVariable(value = "id") Long id, @RequestBody Genre genreDetails) {
        Optional<Genre> genre = genreRepository.findById(id);
        Genre genre_new = genre.get();
        genre_new.setName(genreDetails.getName());
        return genreRepository.save(genre_new);

    }

    @DeleteMapping("/deleteGenre/{id}")
    public boolean deleteBook(@PathVariable(value = "id") Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        Genre genre_new = genre.get();
        genreRepository.delete(genre_new);
        return true;
    }
}
