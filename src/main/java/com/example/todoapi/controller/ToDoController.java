package com.example.todoapi.controller;

import com.example.todoapi.model.ToDo;
import com.example.todoapi.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    @Autowired
    private ToDoService service;

    @GetMapping
    public List<ToDo> findAll(){
        return service.getAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ToDo findById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<ToDo> createToDo(@Valid @RequestBody ToDo toDo){
        ToDo t = service.save(toDo);
        return new ResponseEntity<ToDo>(t, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ToDo> updateTodo(@Valid @RequestBody ToDo toDo){
        ToDo t = service.update(toDo);
        return new ResponseEntity<ToDo>(t, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteToDo(@PathVariable String id){
        String t = service.delete(id);
        if(t != null) return new ResponseEntity<String>(t, HttpStatus.OK);
        else return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
}
