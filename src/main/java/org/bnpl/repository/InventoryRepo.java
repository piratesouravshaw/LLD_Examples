package org.bnpl.repository;


import org.bnpl.Model.Item;

import java.util.HashMap;
import java.util.Map;

public class InventoryRepo {
    Map<String,Integer> inventoryStock=new HashMap<>();
    Map<String, Item> itemMap=new HashMap<>();
    public void addInventory(Item item,Integer count){
        inventoryStock.put(item.getName(),inventoryStock.getOrDefault(item.getName(),0)+count);
        itemMap.put(item.getName(),item);
    }
    public void decreaseInventory(String itemName,Integer count){
        if(!itemMap.containsKey(itemName)){
            throw new RuntimeException("Item not available");
        }
        inventoryStock.put(itemName,inventoryStock.get(itemName)-count);
        if(inventoryStock.get(itemName)==0){
            itemMap.remove(itemName);
        }
    }
    public Item getItem(String itemName){
        return itemMap.get(itemName);
    }

    public Integer getStockForItem(String itemName){
        if(inventoryStock.containsKey(itemName)){
            return inventoryStock.get(itemName);
        }
        return -1;
    }

    public void printInventory(){
        for(String itemName:inventoryStock.keySet()){
            System.out.println(itemName+" Stock=> "+inventoryStock.get(itemName)+" PriceForEach=> "+itemMap.get(itemName).getPrice());
        }
    }
}
