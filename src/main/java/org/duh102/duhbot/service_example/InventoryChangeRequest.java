package org.duh102.duhbot.service_example;

public class InventoryChangeRequest {
    private String username;
    private String item;
    private int changeAmount;
    public InventoryChangeRequest(String username, String item,
                                  int changeAmount) {
        this.username = username;
        this.item = item;
        this.changeAmount = changeAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(int changeAmount) {
        this.changeAmount = changeAmount;
    }
}
