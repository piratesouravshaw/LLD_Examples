package org.bnpl.repository;

import java.util.*;

public class OrderHistory {
    // list of orders for the particular userName
    Map<String, List<String>>orderHistory=new HashMap<>();
    void addOrder(String userId,String orderId){
        if(orderHistory.containsKey(userId)){
            orderHistory.get(userId).add(orderId);
        }
        else{
            List<String>newList=new ArrayList<>();
            newList.add(orderId);
            orderHistory.put(userId,newList);
        }
    }
    List<String> getAllOrders(String userName){
        return orderHistory.getOrDefault(orderHistory.get(userName), Collections.emptyList());
    }
}
