package com.sofija.bookstore.service;

import com.sofija.bookstore.model.User;
import com.sofija.bookstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Resource
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer userId) {
        return userRepository.findById(userId)
                .orElse(null);
    }
}
