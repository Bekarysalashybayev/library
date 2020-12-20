package com.library.automationsystem.book.repository;

import com.library.automationsystem.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
