package org.bnpl.service;

import org.bnpl.Model.Item;
import org.bnpl.Model.Order;
import org.bnpl.Model.User;
import org.bnpl.payment.PaymentStatus;
import org.bnpl.payment.PaymentType;
import org.bnpl.repository.InventoryRepo;
import org.bnpl.repository.OrderRepo;
import org.bnpl.repository.UserRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class UserService {
    UserRepo userRepo=new UserRepo();
    OrderRepo orderRepo=new OrderRepo();
    InventoryRepo inventoryRepo=new InventoryRepo();

    public void addStock(String itemName,Integer quantity,Double price){
        Item item=new Item(itemName,price);
        inventoryRepo.addInventory(item,quantity);
        System.out.println("Printing current inventory");
        inventoryRepo.printInventory();
    }

    public void viewStock(){
        inventoryRepo.printInventory();
    }

    public void registerUser(String userName,Double creditLimit){
        User user=new User(userName,creditLimit);
        userRepo.saveUser(user);
        //userRepo.printAllUser();
    }
    public void decreaseCreditLimit(String userName,Double decreaseBy){
        userRepo.decreaseLimit(userName,decreaseBy);
    }
    public void increaseCreditLimit(String userName,Double increaseBy){
        userRepo.decreaseLimit(userName,increaseBy);
    }

    public void processOrderPrepaid(String userName, List<String>itemNameList, List<Integer> quantityList, PaymentType paymentType){
        User userRef=userRepo.getUser(userName);
        if(userRef!=null){
            Double orderAmount=0.0;
            for(int i=0;i<itemNameList.size();i++){
                if(quantityList.get(i)>inventoryRepo.getStockForItem(itemNameList.get(i))){
                    System.out.println("Stock is less than expected");
                    return;
                }
                orderAmount+=inventoryRepo.getItem(itemNameList.get(i)).getPrice()*quantityList.get(i);
            }
            String orderId= String.valueOf(UUID.randomUUID());
            Order order=new Order(orderId,orderAmount, LocalDate.now(),LocalDate.now().plusDays(30),PaymentType.PREPAID, PaymentStatus.CLEARED);
            orderRepo.saveOrder(order);
            orderRepo.addOrderHistoryForUser(userName,order);
            orderRepo.setPaymentDate(order.getOrderId());
            //update inventory
            for(int i=0;i<itemNameList.size();i++){
                inventoryRepo.decreaseInventory(itemNameList.get(i),quantityList.get(i));
            }
            System.out.println("Order succesfull");
            //orderRepo.printAllOrders();
            return;
        }

        System.out.println("No such user present .. register first");
    }
    public void processOrderBNPL(String userName, List<String>itemNameList, List<Integer> quantityList, PaymentType paymentType){
        User userRef=userRepo.getUser(userName);
        if(userRef!=null){
            Double orderAmount=0.0;
            for(int i=0;i<itemNameList.size();i++){
                if(quantityList.get(i)>inventoryRepo.getStockForItem(itemNameList.get(i))){
                    System.out.println("Stock is less than expected");
                    return;
                }
                orderAmount+=inventoryRepo.getItem(itemNameList.get(i)).getPrice();
            }
            if(userRef.getCreditLimit()<orderAmount){
                System.out.println("Insufficient Credit Limit");
                return;
            }
            userRepo.decreaseLimit(userName,orderAmount);
            String orderId= String.valueOf(UUID.randomUUID());
            Order order=new Order(orderId,orderAmount, LocalDate.now(),LocalDate.now().plusDays(30),PaymentType.BNPL, PaymentStatus.PENDING);
            orderRepo.saveOrder(order);
            orderRepo.addOrderHistoryForUser(userName,order);

            //update inventory
            for(int i=0;i<itemNameList.size();i++){
                inventoryRepo.decreaseInventory(itemNameList.get(i),quantityList.get(i));
            }
            System.out.println("Order succesfull");
            //orderRepo.printAllOrders();
            return;

        }
        System.out.println(("No such user present .. register first"));
    }

    public void view_orders(String userName){
        orderRepo.printOrderHistoryForUserName(userName);
    }

    public void clearPendingOrders(String userName,List<String>orderIds){
        for(String id:orderIds){
            Order order=orderRepo.getOrder(id);
            order.setPaymentStatus(PaymentStatus.CLEARED);
            orderRepo.setPaymentDate(order.getOrderId());
            System.out.println("OrderId => "+order.getOrderId()+" => "+order.getPaymentStatus());
            Double increaseBy=order.getAmt();
            userRepo.increaseLimit(userName,increaseBy);

        }
    }
}
