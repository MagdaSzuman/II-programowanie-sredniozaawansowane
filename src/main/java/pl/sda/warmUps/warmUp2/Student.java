package pl.sda.warmUps.warmUp2;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private Map<Subject, Double> grades;

    public Student addGrade(Subject subject, Double grade){
        if (grades == null){
            grades = new HashMap<>();
        }
        grades.put(subject, grade);
        return this;
    }
}
