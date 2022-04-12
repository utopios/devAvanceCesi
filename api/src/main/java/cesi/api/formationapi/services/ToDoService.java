package cesi.api.formationapi.services;

import cesi.api.formationapi.models.ToDoItem;
import cesi.api.formationapi.models.ToDoList;
import cesi.api.formationapi.repositories.ToDoItemRepository;
import cesi.api.formationapi.repositories.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class ToDoService {

    @Autowired
    private ToDoListRepository _toDoListRepository;

    @Autowired
    private ToDoItemRepository _todoItemRepository;

    //Créer une todolist
    public ToDoList saveTodoList(ToDoList toDoList) {
        return _toDoListRepository.save(toDoList);
    }
    //Créer une todoItem
    public ToDoItem saveTodoItem(int todoListId, ToDoItem toDoItem) {
        ToDoList toDoList = getToDoList(todoListId);
        toDoList.addToDoItem(toDoItem);
        _toDoListRepository.save(toDoList);
        return toDoItem;
    }
    //Récupérer la liste des todoList
   public List<ToDoList> getTodoLists() {
        List todosList = new ArrayList<ToDoList>();
         _toDoListRepository.findAll().forEach(t -> {
            todosList.add(t);
        });
        return todosList;
   }

    //Récupérer une todoList
    public ToDoList getToDoList(int id) {
        return _toDoListRepository.findById(Long.valueOf(id)).get();
    }
    //Récupérer les todoitems d'une todolist
    public List<ToDoItem> getTodoItems(int id) {
        ToDoList toDoList = _toDoListRepository.findById(Long.valueOf(id)).get();
        if(toDoList != null) {
            return toDoList.getToDoItems();
        }
        return null;
    }
    //Récupérer une todoitem
    public ToDoItem getTodoItem(int id) {
        return _todoItemRepository.findById(Long.valueOf(id)).get();
    }
    //Supprimer une todolist, todoitems

    //Supprimer une todoitem
}
