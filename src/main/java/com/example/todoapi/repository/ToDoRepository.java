package com.example.todoapi.repository;

import com.example.todoapi.model.ToDo;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ToDoRepository{
    private List<ToDo> toDos = new ArrayList<ToDo>();

    public List<ToDo> findAll(Map<String, String> params){
        List<ToDo> filteredTodos = toDos;

        //Filter options
        if(params != null) {
            if(params.containsKey("sortDueDate")){
                if(params.get("sortDueDate").equals("desc")) Collections.sort(filteredTodos, ToDo.DueDateComparator);
                else Collections.sort(filteredTodos, ToDo.DueDateComparator.reversed());
            }

            if(params.containsKey("sortPriority")){
                if(params.get("sortPriority").equals("desc")) Collections.sort(filteredTodos, ToDo.PriorityComparator);
                else Collections.sort(filteredTodos, ToDo.PriorityComparator.reversed());
            }

            return filteredTodos.stream()
                    .filter(t ->
                            t.getName().toLowerCase().contains(params.getOrDefault("name", "").toLowerCase()) &&
                                    params.getOrDefault("priority", "1 2 3").contains(t.getPriority().toString()) &&
                                    params.getOrDefault("isDone", "false true").contains(t.getIsDone().toString())
                    ).collect(Collectors.toList());
        }
        //Get all
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
        toDo.setCreatedAt(LocalDateTime.now());

        toDos.add(toDo);

        return toDo;
    }

    public String update(ToDo t){
        int idx = 0;
        String id = "";

        for(int i = 0; i < toDos.size(); i++){
            if(toDos.get(i).getId().equals(t.getId())){
                id = t.getId();
                idx = i;
                break;
            }
        }

        toDos.get(idx).setName(t.getName());
        toDos.get(idx).setDueDate(t.getDueDate());
        toDos.get(idx).setPriority(t.getPriority());

        return "Updated";
    }

    public String markAsDone(String id){
        int idx = 0;
        for(int i = 0; i < toDos.size(); i++){
            if(toDos.get(i).getId().equals(id)){
                idx = i;
                break;
            }
        }

        toDos.get(idx).setIsDone(true);
        toDos.get(idx).setDoneAt(LocalDateTime.now());

        return "Marked as done";
    }

    public String markAsUndone(String id){
        int idx = 0;
        for(int i = 0; i < toDos.size(); i++){
            if(toDos.get(i).getId().equals(id)){
                idx = i;
                break;
            }
        }

        toDos.get(idx).setIsDone(false);
        toDos.get(idx).setDoneAt(null);

        return "Marked as undone";
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
