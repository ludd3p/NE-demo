package com.example.NE_Bookstore.dal;
import com.example.NE_Bookstore.dal.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByUsername(String username);
    Optional<User> findById(Long id);
}
