package com.example.NE_Bookstore.dal;

import com.example.NE_Bookstore.dal.Entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAll();
}
