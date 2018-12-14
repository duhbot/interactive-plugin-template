package org.duh102.duhbot.service_example;

public class InventoryProblem extends Exception {
    private String message;
    public InventoryProblem(String message) {
        this.message = message;
    }
    public String toString() {
        return message;
    }
}
