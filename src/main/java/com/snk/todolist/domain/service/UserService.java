package com.snk.todolist.domain.service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snk.todolist.domain.dto.UserDTO;
import com.snk.todolist.domain.model.User;
import com.snk.todolist.domain.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(UserDTO dto) throws IOException {
        return this.userRepository.save(new User(dto));
    }

    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

}
