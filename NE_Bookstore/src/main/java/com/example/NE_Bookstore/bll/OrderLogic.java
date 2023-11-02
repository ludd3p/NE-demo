package com.example.NE_Bookstore.bll;

import com.example.NE_Bookstore.bll.DTOS.OrderDTO;
import com.example.NE_Bookstore.bll.DTOS.OrderItemDTO;
import com.example.NE_Bookstore.dal.BookRepository;
import com.example.NE_Bookstore.dal.Entities.Book;
import com.example.NE_Bookstore.dal.Entities.Order;
import com.example.NE_Bookstore.dal.Entities.OrderItem;
import com.example.NE_Bookstore.dal.Entities.User;
import com.example.NE_Bookstore.dal.OrderItemRepository;
import com.example.NE_Bookstore.dal.OrderRepository;
import com.example.NE_Bookstore.dal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderLogic {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    Converter converter;

    public ResponseEntity<List<OrderDTO>> getAllOrdersForUser(String username) {
        User user = userRepository.findByUsername(username);
        List<Order> orders = orderRepository.findAllOrdersByUser(user);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order o : orders) {
            orderDTOS.add(converter.convertOrderToDTO(o));
        }
        return ResponseEntity.ok().body(orderDTOS);
    }

    public ResponseEntity<String> addBookToOrder(String username, Long bookId, int quantity) {
        // Find the user
        User user = userRepository.findByUsername(username);

        // Find the user's active order, if one exists
        Optional<Order> optionalActiveOrder = orderRepository.findActiveOrderByUserAndIsCompleted(user, false);

        // If no active order exists, create a new one
        Order order;
        if (optionalActiveOrder.isPresent()) {
            order = optionalActiveOrder.get();
        } else {
            order = new Order();
            order.setUser(user);
            order = orderRepository.save(order);
        }

        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();

            // Check if the book is in stock
            if (book.getStock_count() >= quantity) {
                // Calculate the price based on quantity
                double totalPrice = book.getPrice() * quantity;

                // Check if the total price exceeds the limit
                if (totalPrice + order.getTotal_cost() <= 120) {
                    // Check if the book is already in the order
                    Optional<OrderItem> existingOrderItem = order.getOrderItems()
                            .stream()
                            .filter(item -> item.getBook().getId() == (book.getId()))
                            .findFirst();

                    if (existingOrderItem.isPresent()) {
                        // Update the quantity of the existing OrderItem
                        OrderItem orderItem = existingOrderItem.get();
                        int newQuantity = orderItem.getQuantity() + quantity;
                        orderItem.setQuantity(newQuantity);
                        orderItem.setTotalPrice(book.getPrice() * newQuantity);
                        orderItemRepository.save(orderItem);
                    } else {
                        // Create a new OrderItem
                        OrderItem orderItem = new OrderItem();
                        orderItem.setBook(book);
                        orderItem.setPrice(book.getPrice());
                        orderItem.setQuantity(quantity);
                        orderItem.setOrder(order);
                        orderItem.setTotalPrice(orderItem.getPrice() * quantity);

                        // Save the order item
                        orderItem = orderItemRepository.save(orderItem);

                        // Add the order item to the order
                        order.getOrderItems().add(orderItem);
                    }

                    // Calculate the new stock count
                    int newStockCount = book.getStock_count() - quantity;
                    book.setStock_count(newStockCount);
                    bookRepository.save(book);

                    // Update the order's total price
                    order.setTotal_cost(order.getTotal_cost() + totalPrice);
                    orderRepository.save(order);

                    return ResponseEntity.ok("Book added to the order.");
                } else {
                    return ResponseEntity.badRequest().body("Total price exceeds the limit.");
                }
            } else {
                return ResponseEntity.badRequest().body("Book is out of stock.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<OrderItemDTO> getAllOrderItems(){
        List<OrderItem> orderItems = orderItemRepository.findAll();
        ArrayList<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        for (OrderItem o : orderItems) {
            orderItemDTOS.add(converter.convertOrderItemToDTO(o));
        }
        return orderItemDTOS;
    }


    public ResponseEntity<String> completeOrder(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            Optional<Order> optionalOrder = orderRepository.findActiveOrderByUserAndIsCompleted(userOptional.get(), false);

            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();

                if (!order.isCompleted()) {
                    order.setCompleted(true);
                    orderRepository.save(order);
                    return ResponseEntity.ok("Order completed successfully.");
                } else {
                    return ResponseEntity.badRequest().body("Order is already completed.");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<OrderDTO> getCurrentOrder(String username) {
        User user = userRepository.findByUsername(username);
        Optional<Order> optionalActiveOrder = orderRepository.findActiveOrderByUserAndIsCompleted(user, false);
        if (optionalActiveOrder.isPresent()){
            Order order = optionalActiveOrder.get();
            return ResponseEntity.ok().body(converter.convertOrderToDTO(order)) ;
        }
        return ResponseEntity.noContent().build();
    }
}
