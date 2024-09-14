package com.snk.todolist.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snk.todolist.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByName(String name);
}
