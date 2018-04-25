package se.annawrang.laborationTODO.data;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int priority;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    protected Todo(){}

    public Todo(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    /*
    public Todo(String description, int priority, User user) {
        this.description = description;
        this.priority = priority;
        this.user = user;
    } */

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void removeUser(){
        this.user = null;
    }

}
