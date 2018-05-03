package se.annawrang.laborationTODO.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity{

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String surName;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Todo> todos = new ArrayList<>();


    protected User(){}

    public User(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    @PreRemove
    private void removeUserFromTodos(){
        for (Todo t : todos){
            t.removeUser();
        }
    }
}
