package org.duh102.duhbot.service_example;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> inventoryItems;
    public Inventory() {
        inventoryItems = new HashMap<>();
    }
    public Inventory changeItem(String item, int count) {
        inventoryItems.put(item,
                Math.max(0, inventoryItems.getOrDefault(item, 0) + count));
        return this;
    }
    public ImmutableMap<String, Integer> getInventory() {
        return new ImmutableMap.Builder<String, Integer>().putAll(inventoryItems).build();
    }
}
