package com.snk.todolist.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snk.todolist.domain.dto.TaskDTO;
import com.snk.todolist.domain.model.Task;
import com.snk.todolist.domain.service.TaskService;
import com.snk.todolist.domain.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
//Adicionada anotação para evitar o erro de cors, backend na porta 8081 e front na 4200
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        var tasks = this.taskService.getTasks();

        //Altera o status do response de acordo com a existência de tarefas cadastras
        if (tasks.size() > 0) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }

        return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        var task = this.taskService.findTaskById(id);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @GetMapping("/find/user/{id}")
    public ResponseEntity<List<Task>> getTaskByUserId(@PathVariable Long id) {
        var tasks = this.taskService.findByUserId(id);
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }    

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody TaskDTO dto) {

        //Valida se o usuário existe antes de realizar a inserção de uma nova tarefa
        if (this.userService.getById(dto.userId()) == null) {
            //Se o usuário informado não existir, não será possível adicionar a tarefa
            return new ResponseEntity<Task>(HttpStatus.CONFLICT); 
        }

        return new ResponseEntity<Task>(this.taskService.addTask(dto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id){
        this.taskService.updateTaskStatus(id);
        return new ResponseEntity<Task>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> deleteById(@PathVariable Long id) {
        this.taskService.deleteById(id);
        return new ResponseEntity<Task>(HttpStatus.OK);
    }
    
}
