package com.example.NE_Bookstore.dal;

import com.example.NE_Bookstore.dal.Entities.Order;
import com.example.NE_Bookstore.dal.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findActiveOrderByUserAndIsCompleted(User user, boolean completed);
    List<Order> findAllOrdersByUser(User user);
}
