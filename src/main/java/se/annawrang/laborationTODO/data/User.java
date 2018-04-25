package se.annawrang.laborationTODO.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User{

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String surName;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Todo> todos = new ArrayList<>();


    protected User(){}

    public User(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    public Long getId(){
        return id;
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
