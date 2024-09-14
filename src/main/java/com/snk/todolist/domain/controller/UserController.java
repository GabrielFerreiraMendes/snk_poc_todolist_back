package com.snk.todolist.domain.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snk.todolist.domain.dto.UserDTO;
import com.snk.todolist.domain.model.User;
import com.snk.todolist.domain.service.TaskService;
import com.snk.todolist.domain.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        
        var users = this.userService.getUsers();

                if (users.size() > 0) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<User> getByID(@PathVariable Long id) {
        var user = this.userService.getById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody UserDTO dto) throws IOException {
        var user = this.userService.addUser(dto);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }    

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<User> deletarById(@PathVariable Long id) {
        
        var tasks = this.taskService.findByUserId(id);
        
        if (!tasks.isEmpty()) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }

        this.userService.deleteById(id);

        return new ResponseEntity<User>(HttpStatus.OK);
    }
}
