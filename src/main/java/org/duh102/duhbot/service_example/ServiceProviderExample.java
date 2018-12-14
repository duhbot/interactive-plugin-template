package org.duh102.duhbot.service_example;

import org.duh102.duhbot.functions.ServiceEndpointDefinition;
import org.duh102.duhbot.functions.ServiceProviderPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ServiceProviderExample implements ServiceProviderPlugin {
    public static final String SERVICE_ENDPOINT = "service-example";
    public ConcurrentMap<String, Inventory> inventoryMap =
            new ConcurrentHashMap<>();
    @Override
    public Map<String, ServiceEndpointDefinition> getInteractions() {
        HashMap<String, ServiceEndpointDefinition> serviceMap = new HashMap<>();
        serviceMap.put(InventoryChangeEndpoint.PATH,
                new InventoryChangeEndpoint(inventoryMap));
        serviceMap.put(InventoryViewEndpoint.PATH,
                new InventoryViewEndpoint(inventoryMap));
        return serviceMap;
    }

    @Override
    public String getEndpointRoot() {
        return SERVICE_ENDPOINT;
    }

    @Override
    public String getPluginName() {
        return SERVICE_ENDPOINT;
    }
}
