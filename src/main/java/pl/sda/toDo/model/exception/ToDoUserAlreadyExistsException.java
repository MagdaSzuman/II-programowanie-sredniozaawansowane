package pl.sda.toDo.model.exception;

public class ToDoUserAlreadyExistsException extends ToDoException {

    public ToDoUserAlreadyExistsException(String message) {
        super(message);
    }
}
