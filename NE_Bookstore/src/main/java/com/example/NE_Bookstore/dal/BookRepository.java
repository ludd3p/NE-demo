package com.example.NE_Bookstore.dal;

import com.example.NE_Bookstore.dal.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAll();
    Book findByTitle(String title);
    Optional<Book> findById(Long id);
}
