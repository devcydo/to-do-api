package com.example.todoapi.service;

import com.example.todoapi.model.ToDo;
import com.example.todoapi.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository repository;

    public List<ToDo> getAll(){
        return repository.findAll();
    }

    public ToDo getById(String id){
        return repository.findById(id);
    }

    public ToDo save(ToDo toDo){
        return repository.save(toDo);
    }

    public ToDo update(ToDo toDo){
        return repository.update(toDo);
    }

    public String delete(String id) {
        return repository.delete(id);
    }

}
