package com.example.todoapi.repository;

import com.example.todoapi.model.ToDo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ToDoRepository{
    private List<ToDo> toDos = new ArrayList<ToDo>();

    public List<ToDo> findAll(){
        return toDos;
    }

    public ToDo findById(String id){
        for (int i = 0; i < toDos.size(); i++) {
            if (toDos.get(i).getId().equals(id)) {
                return toDos.get(i);
            }
        }
        return null;
    }

    public ToDo save(ToDo t){
        ToDo toDo = new ToDo();

        //Generate random id
        toDo.setId(UUID.randomUUID().toString());
        toDo.setName(t.getName());
        toDo.setDueDate(t.getDueDate());
        toDo.setIsDone(false);
        toDo.setPriority(t.getPriority());
        toDo.setCreatedAd(LocalDate.now());

        toDos.add(toDo);

        return toDo;
    }

    public ToDo update(ToDo t){
        int idx = 0;
        String id = "";

        for(int i = 0; i < toDos.size(); i++){
            if(toDos.get(i).getId().equals(t.getId())){
                id = t.getId();
                idx = i;
                break;
            }
        }

        ToDo toDo = new ToDo();
        toDo.setId(id);
        toDo.setName(t.getName());
        toDo.setDueDate(t.getDueDate());
        toDo.setPriority(t.getPriority());
        toDos.set(idx, toDo);
        return toDo;
    }

    public String delete(String id){
        if(toDos.removeIf(toDo -> toDo.getId().equals(id))){
            return "Element deleted";
        }
        return null;
    }

    public List<ToDo> search(String name) {
        return toDos.stream().filter(toDo -> toDo.getName().startsWith(name)).collect(Collectors.toList());
    }

}
