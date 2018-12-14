package org.duh102.duhbot.service_example;

import com.google.common.collect.ImmutableMap;
import org.duh102.duhbot.exception.MismatchedServiceResponseClass;
import org.duh102.duhbot.functions.ServiceEndpointDefinition;
import org.duh102.duhbot.functions.ServiceResponse;

import java.util.concurrent.ConcurrentMap;

public class InventoryViewEndpoint implements ServiceEndpointDefinition {
    public static final String PATH = "inventory-view";

    private ConcurrentMap<String, Inventory> inventoryListing;
    public InventoryViewEndpoint(ConcurrentMap<String, Inventory> inventoryListing) {
        this.inventoryListing = inventoryListing;
    }
    @Override
    public Class<?> getRequestClass() {
        return InventoryViewRequest.class;
    }

    @Override
    public Class<?> getResponseClass() {
        return InventoryResponse.class;
    }

    @Override
    public ServiceResponse interact(Object o) {
        Exception problem = new Exception("Unknown error");
        ImmutableMap<String, Integer> result = null;
        InventoryViewRequest request = (InventoryViewRequest)o;
        String username = request.getUsername();
        Inventory inventory;
        try {
            synchronized (inventoryListing) {
                if (!inventoryListing.containsKey(username)) {
                    throw new Exception(String.format("No inventory for user " +
                            "%s", username));
                }
                inventory = inventoryListing.get(username);
            }
            result = inventory.getInventory();
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
