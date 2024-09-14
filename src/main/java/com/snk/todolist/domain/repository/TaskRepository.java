package com.snk.todolist.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snk.todolist.domain.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
    
}
