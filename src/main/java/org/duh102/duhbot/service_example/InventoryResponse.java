package org.duh102.duhbot.service_example;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class InventoryResponse {
    private final ImmutableMap<String, Integer> inventoryState;
    private final Exception exception;
    public InventoryResponse(Map<String, Integer> inventoryState,
                             Exception exception) {
        this.inventoryState = new ImmutableMap.Builder<String,
                Integer>().putAll(inventoryState).build();
        this.exception = exception;
    }
    public InventoryResponse(ImmutableMap<String, Integer> inventoryState,
                             Exception exception) {
        this.inventoryState = inventoryState;
        this.exception = exception;
    }
    public InventoryResponse(Map<String, Integer> inventoryState) {
        this(inventoryState, null);
    }
    public InventoryResponse(ImmutableMap<String, Integer> inventoryState) {
        this(inventoryState, null);
    }

    public Map<String, Integer> get() throws Exception {
        Exception e = getException();
        if( e != null )
            throw e;
        return getInventoryState();
    }

    public Map<String, Integer> getInventoryState() {
        return inventoryState;
    }

    public Exception getException() {
        return exception;
    }
}
