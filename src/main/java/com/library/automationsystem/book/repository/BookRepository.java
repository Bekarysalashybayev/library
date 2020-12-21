package com.library.automationsystem.book.repository;

import com.library.automationsystem.book.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByGenreId(Long genreId);
    List<Book> findByPublisherId(Long authorId);
    List<Book> findByPublisherIdAndGenreId(Long authorId, Long genreId);
}
