package com.example.NE_Bookstore.pl;

import com.example.NE_Bookstore.bll.DTOS.OrderDTO;
import com.example.NE_Bookstore.bll.DTOS.OrderItemDTO;
import com.example.NE_Bookstore.bll.DTOS.UserDTO;
import com.example.NE_Bookstore.bll.OrderLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books/order")
public class OrderAPI {
    @Autowired
    OrderLogic orderLogic;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrdersForUser(@RequestParam("username") String username) {
        return orderLogic.getAllOrdersForUser(username);
    }

    @PostMapping(value = "/add-book", consumes = "application/json")
    public ResponseEntity<String> addBookToOrder(@RequestBody Map<String, Object> addBookToOrderRequest) {
        String username = (String) addBookToOrderRequest.get("username");
        Long bookId = ((Number) addBookToOrderRequest.get("bookid")).longValue();
        return orderLogic.addBookToOrder(username, bookId, 1);
    }

    @GetMapping("/orderitems")
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems(){
        return ResponseEntity.ok().body(orderLogic.getAllOrderItems());
    }

    @GetMapping("/active")
    public ResponseEntity<OrderDTO> getCurrentOrder(@RequestParam("username") String username){
        return orderLogic.getCurrentOrder(username);
    }

    @PostMapping("/complete")
    public ResponseEntity<String> completeOrder(@RequestBody UserDTO user) {
        return orderLogic.completeOrder(user.getId());
    }

}
