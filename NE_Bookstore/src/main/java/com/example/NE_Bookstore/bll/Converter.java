package com.example.NE_Bookstore.bll;

import com.example.NE_Bookstore.bll.DTOS.BookDTO;
import com.example.NE_Bookstore.bll.DTOS.OrderDTO;
import com.example.NE_Bookstore.bll.DTOS.OrderItemDTO;
import com.example.NE_Bookstore.bll.DTOS.UserDTO;
import com.example.NE_Bookstore.dal.Entities.Book;
import com.example.NE_Bookstore.dal.Entities.Order;
import com.example.NE_Bookstore.dal.Entities.OrderItem;
import com.example.NE_Bookstore.dal.Entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {
    public OrderDTO convertOrderToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setTotalCost(order.getTotal_cost());
        orderDTO.setUser(convertUserToDTO(order.getUser()));
        orderDTO.setCompleted(order.isCompleted());
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        System.out.println("\u001B[31m" + order.getOrderItems().size() +"\u001B[0m");
        for (OrderItem o : order.getOrderItems()){
            System.out.println("\u001B[31m" + o.getBook() +"\u001B[0m");
            orderItemDTOS.add(convertOrderItemToDTO(o));
        }
        orderDTO.setOrderItems(orderItemDTOS);
        return orderDTO;
    }

    public OrderItemDTO convertOrderItemToDTO(OrderItem o) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(o.getId());
        orderItemDTO.setOrderId(o.getOrder().getId());
        orderItemDTO.setBook(convertBookToDTO(o.getBook()));
        orderItemDTO.setPrice(o.getPrice());
        orderItemDTO.setQuantity(o.getQuantity());
        orderItemDTO.setTotalPrice(o.getTotalPrice());

        return orderItemDTO;
    }

    public UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    public User convertDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        if (userDTO.getId() != null)
            user.setId(userDTO.getId());
        return user;
    }

    public BookDTO convertBookToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setStock_count(book.getStock_count());
        bookDTO.setLimited(book.isLimited());
        return bookDTO;
    }

    public Book convertDTOToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setPrice(bookDTO.getPrice());
        book.setStock_count(bookDTO.getStock_count());
        book.setLimited(bookDTO.isLimited());
        return book;
    }
}
