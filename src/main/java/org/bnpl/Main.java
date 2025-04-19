package org.bnpl;

import org.bnpl.payment.PaymentType;
import org.bnpl.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //InventoryProcessor inventoryProcessor=new InventoryProcessor();
        UserService userService=new UserService();
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("Enter the command");
            String command=sc.nextLine();
            String[] commandList=command.split(" ");
            if(commandList[0].equalsIgnoreCase("buy")){
                System.out.println("Enter userName");
                String userName=sc.next();
                List<String> itemNameList=new ArrayList<>();
                List<Integer>quantityList=new ArrayList<>();
                String itemName="";
                Integer quantity=0;
                while(!itemName.equalsIgnoreCase("-1")){
                    System.out.println("Enter itemName -- if end of list press -1");
                    itemName=sc.next();
                    System.out.println("Enter quantity -- if end of list press -1");
                    quantity=sc.nextInt();
                    if(!itemName.equalsIgnoreCase("-1")) {
                        itemNameList.add(itemName);
                        quantityList.add(quantity);
                    }
                }
                System.out.println("Enter payment Type");
                String paymentMethod=sc.next();
                if(paymentMethod.equalsIgnoreCase("PREPAID")){
                    userService.processOrderPrepaid(userName,itemNameList,quantityList, PaymentType.PREPAID);
                }
                else{
                    userService.processOrderBNPL(userName,itemNameList,quantityList,PaymentType.BNPL);
                }

            }
            else if(commandList[0].equalsIgnoreCase("clear")){
                // clear userName List<OrderId>
                String userName=commandList[1];

                List<String>orderIds=new ArrayList<>();
                String orderId="initialValue";
                while(!orderId.equalsIgnoreCase("-1")){
                    System.out.println("Enter Order id's for clearance ... else press -1");
                    orderId=sc.next();
                    if(!orderId.equalsIgnoreCase("-1")) orderIds.add(orderId);
                }
                userService.clearPendingOrders(userName,orderIds);
                System.out.println("Order Ids cleared");
            }
            else{
                // seed name count price
                if(commandList[0].equalsIgnoreCase("seed")){
                    String itemName=commandList[1];
                    Integer count=Integer.parseInt(commandList[2]);
                    Double price=Double.parseDouble(commandList[3]);
                    userService.addStock(itemName,count,price);
                }
                else if(commandList[0].equalsIgnoreCase("view")){
                    userService.viewStock();
                }
                else if(commandList[0].equalsIgnoreCase("register")){
                    // register username creditLimit
                    String userName=commandList[1];
                    Double creditLimit=Double.parseDouble(commandList[2]);
                    userService.registerUser(userName,creditLimit);
                }
                else if(commandList[0].equalsIgnoreCase("view_orders")){
                    // view_orders username
                    String userName=commandList[1];
                    userService.view_orders(userName);
                }
            }
        }
    }
}