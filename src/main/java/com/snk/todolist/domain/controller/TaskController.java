package com.snk.todolist.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snk.todolist.domain.dto.TaskDTO;
import com.snk.todolist.domain.dto.UserDTO;
import com.snk.todolist.domain.model.Task;
import com.snk.todolist.domain.model.User;
import com.snk.todolist.domain.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        var tasks = this.taskService.getTasks();

        System.out.println(tasks);


        if (tasks.size() > 0) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }

        return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody TaskDTO dto) {
        return new ResponseEntity<Task>(this.taskService.addTask(dto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id){
        this.taskService.updateTaskStatus(id);
        return new ResponseEntity<Task>(HttpStatus.OK);
    }
    
}
