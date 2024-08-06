package com.piggy.customers.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CustomerOrder {

    @NotNull
    private int orderId;

    @Size(min=2)
    private String status;

    @Size(min=3)
    private String address;

    @Min(10000)
    private int mobile;

    @NotNull
    private int itemId;

    public CustomerOrder(int orderId, String status, String address, int mobile, int itemId) {
        this.orderId=orderId;
        this.status = status;
        this.address = address;
        this.mobile = mobile;
        this.itemId = itemId;
    }

    public @Size(min = 2) String getStatus() {
        return status;
    }

    public void setStatus(@Size(min = 2) String status) {
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

    @NotNull
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(@NotNull int orderId) {
        this.orderId = orderId;
    }
}
