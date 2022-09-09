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
import java.util.Map;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    @Autowired
    private ToDoService service;

//    @GetMapping("{params}")
    @RequestMapping(method = RequestMethod.GET)
    public List<ToDo> findAll(@RequestParam(required = false) Map<String, String> params){
        return service.getAll(params);
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
    public ResponseEntity<String> updateTodo(@Valid @RequestBody ToDo toDo){
        String t = service.update(toDo);
        return new ResponseEntity<String>(t, HttpStatus.OK);
    }

    @PostMapping("{id}/done")
    public ResponseEntity<String> markAsDone(@PathVariable String id){
        String t = service.markAsDone(id);
        return new ResponseEntity<String>(t, HttpStatus.OK);
    }

    @PutMapping("{id}/undone")
    public ResponseEntity<String> markAsUndone(@PathVariable String id){
        String t = service.markAsUndone(id);
        return new ResponseEntity<String>(t, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteToDo(@PathVariable String id){
        String t = service.delete(id);
        if(t != null) return new ResponseEntity<String>(t, HttpStatus.OK);
        else return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
}
