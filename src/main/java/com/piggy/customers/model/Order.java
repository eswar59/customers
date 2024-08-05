package com.piggy.customers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity(name="Orders_Table")
public class Order {

    @Id
    @GeneratedValue
    private int id;

    @Size(min=2)
    private String status;

    @Size(min=3)
    private String address;


    @Min(10000)
    private int mobile;

    @NotNull
    private int itemId;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Order(int id, String status, String address, int mobile, int itemId) {
        this.id = id;
        this.status = status;
        this.address = address;
        this.mobile = mobile;
        this.itemId=itemId;
    }

    public Order(){ }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public @Size(min = 3) String getAddress() {
        return address;
    }

    public void setAddress(@Size(min = 3) String address) {
        this.address = address;
    }

    @Min(10000)
    public int getMobile() {
        return mobile;
    }

    public void setMobile(@Min(10000) int mobile) {
        this.mobile = mobile;
    }

    @NotNull
    public int getItemId() {
        return itemId;
    }

    public void setItemId(@NotNull int itemId) {
        this.itemId = itemId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
