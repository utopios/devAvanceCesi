package cesi.api.formationapi.repositories;

import cesi.api.formationapi.models.ToDoList;

import org.springframework.data.repository.CrudRepository;

public interface ToDoListRepository extends CrudRepository<ToDoList, Long> {
}
