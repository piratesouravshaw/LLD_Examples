package org.bnpl.repository;

import org.bnpl.Model.Order;

import java.time.LocalDate;
import java.util.*;

public class OrderRepo {
    //orderId ---  actual Order object
    Map<String,Order>allOrders=new HashMap<>();
    Map<String,List<Order>>userOrders=new HashMap<>();
    Map<String, LocalDate> paymentDate=new HashMap<>();
    public void saveOrder(Order order){
        allOrders.put(order.getOrderId(),order);
    }
    public void setPaymentDate(String orderId){
        paymentDate.put(orderId,LocalDate.now());
    }
    public void addOrderHistoryForUser(String userName,Order order){
        List<Order>allOrderForThisUser;
        if(userOrders.containsKey(userName)){
            allOrderForThisUser=userOrders.get(userName);
        }
        else{
            allOrderForThisUser=new ArrayList<>();
        }
        allOrderForThisUser.add(order);
        userOrders.put(userName,allOrderForThisUser);
    }
    public Order getOrder(String orderId){
        return allOrders.get(orderId);
    }

    public List<Order> getOrderListFromUserName(String userName){
        return userOrders.getOrDefault(userName, Collections.emptyList());
    }

    public void printOrderHistoryForUserName(String userName){
        List<Order>orderList=userOrders.get(userName);
        System.out.println("Printing all orders of this user"+userName);
        for(int i=0;i<orderList.size();i++){
            orderList.get(i).printOrder();
        }
    }

    public void printAllOrders(){
        for(int i=0;i<allOrders.size();i++){
            allOrders.get(i).printOrder();
        }
    }
}
