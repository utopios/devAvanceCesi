package cesi.api.formationapi.controllers;

import cesi.api.formationapi.models.ToDoItem;
import cesi.api.formationapi.models.ToDoList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("todolists")
public class ToDoController {

    @GetMapping("")
    public List<ToDoList> getTodoLists() {
        return null;
    }

    @GetMapping("/filter/{title}")
    public List<ToDoList> searchTodoList(@PathVariable String title) {
        return null;
    }

    @GetMapping("{id}")
    public ToDoList getTodoList(@PathVariable int id) {
        return null;
    }


    @PostMapping("")
    public ToDoList createTodoList(@RequestBody ToDoList toDoList) {
        return null;
    }

    @DeleteMapping("{id}")
    public boolean deleteTodoList(@PathVariable int id) {
        return false;
    }

    @GetMapping("{todoListId}/todoitems")
    public List<ToDoItem> getTodoItems(@PathVariable int todoListId) {
        return null;
    }

    @PostMapping("{todoListId}/todoitems")
    public ToDoItem createTodoItem(@PathVariable int todoListId, @RequestBody ToDoItem toDoItem) {
        return null;
    }

    @GetMapping("{todoListId}/todoitems/{todoItemId}")
    public ToDoItem createTodoItem(@PathVariable int todoListId, @PathVariable int todoItemId) {
        return null;
    }

    @DeleteMapping("{todoListId}/todoitems/{todoItemId}")
    public boolean deleteToDoItem(@PathVariable int todoListId, @PathVariable int todoItemId) {
        return false;
    }


}
