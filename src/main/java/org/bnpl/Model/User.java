package org.bnpl.Model;

public class User {
    String userName;
    Double creditLimit;

    public User(String userName, Double creditLimit) {
        this.userName = userName;
        this.creditLimit = creditLimit;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }
}
