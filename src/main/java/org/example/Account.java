package org.example;

public class Account {

    private boolean active;
    private Address defaultDelivaryAddress;

    public Account() {
        this.active = false;
    }

    public void active() {
        this.active = true;
    }

    public boolean isActive() {
        return this.active;
    }

    public Address getDefaultDelivaryAddress() {
        return defaultDelivaryAddress;
    }

    public void setDefaultDelivaryAddress(Address defaultDelivaryAddress) {
        this.defaultDelivaryAddress = defaultDelivaryAddress;
    }
}
