package com.example.NE_Bookstore.pl;

import com.example.NE_Bookstore.bll.DTOS.BookDTO;
import com.example.NE_Bookstore.bll.BookstoreLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookstoreAPI {
    @Autowired
    BookstoreLogic bookstoreLogic;


    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookstoreLogic.getAllBooks();
        return ResponseEntity.ok().body(books);
    }

    @PutMapping("/restock")
    public ResponseEntity<String> restockBook(@RequestBody Map<String, Object> restockRequest) {
        String username = (String) restockRequest.get("username");
        String password = (String) restockRequest.get("password");
        Long bookId = ((Number) restockRequest.get("bookid")).longValue();
        return bookstoreLogic.restockBook(username, password, bookId);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Void> addBookToDB(@RequestBody BookDTO bookRequest) {
        bookstoreLogic.addBookToDB(bookRequest);
        return ResponseEntity.noContent().build();
    }
}
