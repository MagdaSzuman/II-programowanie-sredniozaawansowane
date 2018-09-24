package pl.sda.toDo.model;

import java.time.Instant;

public class ToDo {
    private String id;
    private String name;
    private String description;
    private Instant creationDate;
    private ToDoUser owner;
    private ToDostatus toDoStatus;
}
