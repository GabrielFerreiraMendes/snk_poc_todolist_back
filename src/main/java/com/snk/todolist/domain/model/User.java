package com.snk.todolist.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.snk.todolist.domain.dto.UserDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private List<Task> tasks = new ArrayList<Task>();
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
   
    public void setName(String name) {
        this.name = name;
    }

    // Constructor
    public User(UserDTO dto) {
        this.setName(dto.name());
    }
}
