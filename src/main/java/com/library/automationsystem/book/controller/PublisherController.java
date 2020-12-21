package com.library.automationsystem.book.controller;

import com.library.automationsystem.book.model.Genre;
import com.library.automationsystem.book.model.Publisher;
import com.library.automationsystem.book.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class PublisherController {
    @Autowired
    PublisherRepository publisherRepository;

    @GetMapping("/getAllAuthors")
    public List<Publisher> getAllAuthors() {
        return publisherRepository.findAll();
    }

    @GetMapping("/getAuthorById/{id}")
    public Optional<Publisher> getAuthorById(@PathVariable(value = "id") Long id) {
        return publisherRepository.findById(id);
    }

    @PostMapping("/addAuthor")
    public Publisher addAuthor(@RequestBody Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @PutMapping("/updateAuthor/{id}")
    public Publisher updateAuthor(@PathVariable(value = "id") Long id, @RequestBody Publisher publisherDetails) {
        Optional<Publisher> genre = publisherRepository.findById(id);
        Publisher publisher_new = genre.get();
        publisher_new.setName(publisherDetails.getName());
        return publisherRepository.save(publisher_new);

    }

    @DeleteMapping("/deleteAuthor/{id}")
    public boolean deleteAuthor(@PathVariable(value = "id") Long id) {
        Optional<Publisher> publisher = publisherRepository.findById(id);
        Publisher publisher_new = publisher.get();
        publisherRepository.delete(publisher_new);
        return true;
    }
}
