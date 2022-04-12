package cesi.api.formationapi.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "todoitem")
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "taskname")
    private String taskName;

    private boolean done;

    @ManyToOne
    @JoinColumn(name = "todolist_id", nullable = false)
    private ToDoList toDoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }
}
