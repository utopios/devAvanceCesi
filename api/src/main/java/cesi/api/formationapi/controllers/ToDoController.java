package cesi.api.formationapi.controllers;

import cesi.api.formationapi.models.ToDoList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("todolists")
public class ToDoController {

    @GetMapping("/")
    public List<ToDoList> getTodoLists() {
        return null;
    }

    @GetMapping("/{title}")
    public List<ToDoList> searchTodoList(@PathVariable String title) {
        return null;
    }

    @GetMapping("/{id}")
    public ToDoList getTodoList(@PathVariable int id) {
        return null;
    }


    @PostMapping("/")
    public ToDoList CreateTodoList(@RequestBody ToDoList toDoList) {
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean DeleteTodoList(@PathVariable int id) {
        return false;
    }

}
