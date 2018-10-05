package pl.sda.fileAndJackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private int age;
    private List<PhoneNumber>phoneNumbers;

    @JsonCreator
    public User(@JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName,
                @JsonProperty("age") int age,
                @JsonProperty("phoneNumbers") List<PhoneNumber> phoneNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumbers = phoneNumbers;
    }

    public static class PhoneNumber{
        private String number;
        private String name;

        @JsonCreator
        public PhoneNumber(@JsonProperty("number") String number, @JsonProperty("name") String name) {
            this.number = number;
            this.name = name;
        }
    }
}
