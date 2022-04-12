package cesi.api.formationapi.controllers;

import cesi.api.formationapi.dtos.ToDoItemDTO;
import cesi.api.formationapi.models.ToDoItem;
import cesi.api.formationapi.models.ToDoList;
import cesi.api.formationapi.services.ToDoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("todolists")
public class ToDoController {

    @Autowired
    private ToDoService _todoService;

    private ModelMapper modelMapper;

    public ToDoController() {
        modelMapper = new ModelMapper();
    }

    @GetMapping("")
    public List<ToDoList> getTodoLists() {
        return  _todoService.getTodoLists();
    }

    @GetMapping("/filter/{title}")
    public List<ToDoList> searchTodoList(@PathVariable String title) {
        return null;
    }

    @GetMapping("{id}")
    public ToDoList getTodoList(@PathVariable int id) {
        return _todoService.getToDoList(id);
    }


    @PostMapping("")
    public ToDoList createTodoList(@RequestBody ToDoList toDoList) {
        return _todoService.saveTodoList(toDoList);
    }

    @DeleteMapping("{id}")
    public boolean deleteTodoList(@PathVariable int id) {
        return false;
    }

    @GetMapping("{todoListId}/todoitems")
    public List<ToDoItem> getTodoItems(@PathVariable int todoListId) {
        return _todoService.getTodoItems(todoListId);
    }

    @PostMapping("{todoListId}/todoitems")
    public ToDoItemDTO createTodoItem(@PathVariable int todoListId, @RequestBody ToDoItemDTO toDoItemDTO) {
        /*ToDoItem toDoItem = new ToDoItem();
        toDoItem.setTaskName(toDoItemDTO.getTaskName());*/
        ToDoItem toDoItem = modelMapper.map(toDoItemDTO, ToDoItem.class);
        _todoService.saveTodoItem(todoListId, toDoItem);
        return modelMapper.map(toDoItem, ToDoItemDTO.class);
    }

    @GetMapping("{todoListId}/todoitems/{todoItemId}")
    public ToDoItem getTodoItem(@PathVariable int todoListId, @PathVariable int todoItemId) {
        return _todoService.getTodoItem(todoItemId);
    }

    @DeleteMapping("{todoListId}/todoitems/{todoItemId}")
    public boolean deleteToDoItem(@PathVariable int todoListId, @PathVariable int todoItemId) {
        return false;
    }


}
