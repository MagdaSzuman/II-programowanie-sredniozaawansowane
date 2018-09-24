package pl.sda.toDo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class ToDo {
    private String id;
    private String name;
    private ToDoUser creator;
    private String description;
    private Instant creationDate;
    private ToDoUser owner;
    private ToDoStatus toDoStatus;

    public ToDo(String name, ToDoUser creator) {

        this.name = name;
        this.creator = creator;
        this.description = "";
        this.creationDate = Instant.now();
        this.owner = null;
        this.toDoStatus = ToDoStatus.NEW;
        this.id = UUID.randomUUID().toString();
    }
}
