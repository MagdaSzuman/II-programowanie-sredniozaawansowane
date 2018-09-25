package pl.sda.toDo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ToDoUser {
    public static ToDoUser unasigned(){
      return new ToDoUser("Nieprzypisano", null);
    };
    private String name;
    private String password;


}
