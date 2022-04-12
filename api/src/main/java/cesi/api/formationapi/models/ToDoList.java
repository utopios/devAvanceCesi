package cesi.api.formationapi.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private boolean complete;

    private Date endDate;

    @OneToMany(mappedBy = "todoList", fetch = FetchType.LAZY)
    private List<ToDoItem> toDoItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void addToDoItem(ToDoItem toDoItem) {
        toDoItems.add(toDoItem);
    }

    public List<ToDoItem> getToDoItems() {
        return toDoItems;
    }
}
