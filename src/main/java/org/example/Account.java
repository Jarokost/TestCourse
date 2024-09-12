package org.example;

public class Account {

    private boolean active;

    public Account() {
        this.active = false;
    }

    public void active() {
        this.active = true;
    }

    public boolean isActive() {
        return this.active;
    }
}
