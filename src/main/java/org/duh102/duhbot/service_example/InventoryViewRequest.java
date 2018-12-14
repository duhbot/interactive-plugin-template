package org.duh102.duhbot.service_example;

public class InventoryViewRequest {
    private String username;
    public InventoryViewRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
