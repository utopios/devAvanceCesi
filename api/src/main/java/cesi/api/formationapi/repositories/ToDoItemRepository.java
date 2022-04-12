package cesi.api.formationapi.repositories;

import cesi.api.formationapi.models.ToDoItem;
import org.apache.juli.logging.Log;
import org.springframework.data.repository.CrudRepository;

public interface ToDoItemRepository extends CrudRepository<ToDoItem, Long> {
}
