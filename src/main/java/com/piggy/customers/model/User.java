package com.piggy.customers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name= "Customer_Details")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Size(min = 3)
    private String username;

    @Size(min=3)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Size(min=3)
    private String roles;

    @OneToMany(mappedBy = "user")
    //@JsonIgnore
    private List<Order> orders;

    public User (int id, String username, String password, String roles){
        this.id=id;
        this.username=username;
        this.password=password;
        this.roles=roles;
    }

    public User(){ }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public @Size(min = 3) String getRoles() {
        return roles;
    }

    public void setRoles(@Size(min = 3) String roles) {
        this.roles = roles;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
