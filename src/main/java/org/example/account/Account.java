package org.example.account;

public class Account {

    private boolean active;
    private Address defaultDelivaryAddress;
    private String email;

    public Account() {
        this.active = false;
    }

    public Account(Address defaultDelivaryAddress) {
        this.defaultDelivaryAddress = defaultDelivaryAddress;
        if(defaultDelivaryAddress != null) {
            active();
        } else {
            this.active = false;
        }
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

    public void setEmail(String email) {

        if(email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Wrong email format");
        }
    }

    public String getEmail() {
        return this.email;
    }
}
