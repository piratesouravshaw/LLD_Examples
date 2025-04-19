package org.bnpl.repository;

import org.bnpl.Model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepo {
    Map<String, User> userRepo=new HashMap<>();
    public void saveUser(User user){
        userRepo.put(user.getUserName(),user);
    }
    public User getUser(String userName){
        if(userRepo.containsKey(userName))return userRepo.get(userName);
        return null;
    }
    public void increaseLimit(String userName,Double increaseBy){
        User userRef=userRepo.get(userName);
        userRef.setCreditLimit(userRef.getCreditLimit()+increaseBy);
        userRepo.put(userName,userRef);
    }
    public void decreaseLimit(String userName,Double decreaseBy){
        User userRef=userRepo.get(userName);
        userRef.setCreditLimit(userRef.getCreditLimit()-decreaseBy);
        userRepo.put(userName,userRef);
    }
    public void printAllUser(){
        for(String userName:userRepo.keySet()){
            System.out.println(userName+" => "+userRepo.get(userName).getCreditLimit());
        }
    }
}
