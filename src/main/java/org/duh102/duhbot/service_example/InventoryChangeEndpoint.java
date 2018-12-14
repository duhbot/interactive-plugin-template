package org.duh102.duhbot.service_example;

import com.google.common.collect.ImmutableMap;
import org.duh102.duhbot.exception.MismatchedServiceResponseClass;
import org.duh102.duhbot.functions.ServiceEndpointDefinition;
import org.duh102.duhbot.functions.ServiceResponse;

import java.util.concurrent.ConcurrentMap;

public class InventoryChangeEndpoint implements ServiceEndpointDefinition {
    public static final String PATH = "inventory-change";

    private ConcurrentMap<String, Inventory> inventoryListing;
    public InventoryChangeEndpoint(ConcurrentMap<String, Inventory> inventoryListing) {
        this.inventoryListing = inventoryListing;
    }
    @Override
    public Class<?> getRequestClass() {
        return InventoryChangeRequest.class;
    }

    @Override
    public Class<?> getResponseClass() {
        return InventoryResponse.class;
    }

    @Override
    public ServiceResponse interact(Object o) {
        Exception problem;
        ImmutableMap<String, Integer> result = null;
        InventoryChangeRequest request = (InventoryChangeRequest)o;
        String username = request.getUsername();
        String item = request.getItem();
        int count = request.getChangeAmount();
        Inventory inventory;
        try {
            synchronized (inventoryListing) {
                if( count > 0) {
                    if( !inventoryListing.containsKey(username) ) {
                        inventoryListing.put(username, new Inventory());
                    }
                } else {
                    if (!inventoryListing.containsKey(username)) {
                        throw new InventoryProblem(String.format("No inventory for user " +
                                "%s", username));
                    }
                }
                inventory = inventoryListing.get(username);
            }
            synchronized (inventory) {
                if(count < 0) {
                    ImmutableMap<String, Integer> invListing =
                            inventory.getInventory();
                    if( !invListing.containsKey(item) ) {
                        throw new InventoryProblem(String.format("User %s inventory " +
                                        "contains no %s, cannot remove any", username
                                , item));
                    } else if( invListing.get(item) < Math.abs(count)) {
                        throw new InventoryProblem(String.format("User %s inventory " +
                                        "has %d %s, cannot remove %d", username,
                                invListing.get(item), item, Math.abs(count)));
                    }
                }
                inventory.changeItem(item, count);
                result = inventory.getInventory();
                problem = null;
            }
        } catch( Exception e ) {
            problem = e;
        }
        try {
            return new ServiceResponse<>(InventoryResponse.class,
                    new InventoryResponse(result, problem));
        } catch( MismatchedServiceResponseClass msrc ) {
            msrc.printStackTrace();
            return null;
        }
    }
}
