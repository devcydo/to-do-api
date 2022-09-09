package com.example.todoapi.service;

import com.example.todoapi.model.ToDo;
import com.example.todoapi.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository repository;

    public List<ToDo> getAll(Map<String, String> params){
        return repository.findAll(params);
    }

    public ToDo getById(String id){
        return repository.findById(id);
    }

    public ToDo save(ToDo toDo){
        return repository.save(toDo);
    }

    public String update(ToDo toDo){
        return repository.update(toDo);
    }

    public String markAsDone(String id){
        return repository.markAsDone(id);
    }

    public String markAsUndone(String id){
        return repository.markAsUndone(id);
    }

    public String delete(String id) {
        return repository.delete(id);
    }

}
