package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    User findById(Integer id);
    User save(User user);
    User update(User user);
    void delete(Integer id);
    Optional<User> findByUsername(String username);
}
