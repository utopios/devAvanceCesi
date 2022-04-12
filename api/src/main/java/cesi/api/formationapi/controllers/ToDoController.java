package cesi.api.formationapi.controllers;

import cesi.api.formationapi.dtos.ToDoItemDTO;
import cesi.api.formationapi.dtos.ToDoListDTO;
import cesi.api.formationapi.models.ToDoItem;
import cesi.api.formationapi.models.ToDoList;
import cesi.api.formationapi.services.ToDoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<ToDoListDTO> getTodoLists() {
        List<ToDoListDTO> toDoListDTOS = new ArrayList<>();
        _todoService.getTodoLists().forEach(t -> {
            toDoListDTOS.add(modelMapper.map(t, ToDoListDTO.class));
        });
        return  toDoListDTOS;
    }

    @GetMapping("/filter/{title}")
    public List<ToDoList> searchTodoList(@PathVariable String title) {
        return null;
    }

    @GetMapping("{id}")
    public ToDoListDTO getTodoList(@PathVariable int id) {
        return  modelMapper.map(_todoService.getToDoList(id), ToDoListDTO.class);
    }


    @PostMapping("")
    public ToDoListDTO createTodoList(@RequestBody ToDoListDTO toDoListDTO) {
        ToDoList toDoList = _todoService.saveTodoList(modelMapper.map(toDoListDTO, ToDoList.class));
        return modelMapper.map(toDoList, ToDoListDTO.class);
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
