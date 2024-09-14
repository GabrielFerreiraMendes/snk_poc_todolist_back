package com.snk.todolist.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snk.todolist.domain.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
    public List<Task> findByUserId(Long userId);
}
