package com.example.NE_Bookstore.bll;

import com.example.NE_Bookstore.bll.DTOS.BookDTO;
import com.example.NE_Bookstore.dal.Entities.Book;
import com.example.NE_Bookstore.dal.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookstoreLogic {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    Converter converter;

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> booksDTO = new ArrayList<>();
        for (Book b: books) {
            booksDTO.add(converter.convertBookToDTO(b));
        }
        return booksDTO;
    }

    public BookDTO getBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            return converter.convertBookToDTO(book);
        }
        return null;
    }

    public BookDTO getBookByTitle(String title) {
        return converter.convertBookToDTO(bookRepository.findByTitle(title));
    }

    public ResponseEntity<String> restockBook(String username, String password, Long id) {
        if (username.equals("Uncle_Bob_1337") && password.equals("TomCruiseIsUnder170cm")) {
            Optional<Book> bookOptional = bookRepository.findById(id);
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                if (!book.isLimited()) {
                    book.setStock_count(book.getStock_count() + 10);
                    bookRepository.save(book);
                    return ResponseEntity.ok().body("Successfully restocked " + book.getTitle());
                }
                else
                    return ResponseEntity.badRequest().body(book.getTitle() + " is limited and can not be restocked.");
            } else
                return ResponseEntity.badRequest().body("The book could not be located :(");
        } else
            return ResponseEntity.badRequest().body("Wrong username and password combination!!!");
    }

    public void addBookToDB(BookDTO bookDTO){
        Book book = converter.convertDTOToBook(bookDTO);
        bookRepository.save(book);
    }



}
