package pl.sda.toDo.model.exception;

public class ToDoException extends RuntimeException {
    public ToDoException(String message) {
        super(message);
    }
}
