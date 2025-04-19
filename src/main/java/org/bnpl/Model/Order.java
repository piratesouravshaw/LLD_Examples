package org.bnpl.Model;

import org.bnpl.payment.PaymentStatus;
import org.bnpl.payment.PaymentType;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Order {
    String orderId;
    Double amt;
    LocalDate purchaseDate;
    LocalDate dueDate;
    PaymentType paymentType;
    PaymentStatus paymentStatus;

    public Order(String orderId, Double amt, LocalDate purchaseDate, LocalDate dueDate, PaymentType paymentType, PaymentStatus paymentStatus) {
        this.orderId = orderId;
        this.amt = amt;
        this.purchaseDate = purchaseDate;
        this.dueDate = dueDate;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public Double getAmt() {
        return amt;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void printOrder(){
        System.out.print("orderId => "+ this.getOrderId()+
                "\n amount =>"+ this.getAmt()+"\n purchaseDate => "+this.getPurchaseDate()+"\n dueDate=> "+this.getDueDate()+"\n paymentType=> "+this.getPaymentType());
        if(DAYS.between(this.getPurchaseDate(),this.getDueDate())>30){
            this.setPaymentStatus(PaymentStatus.PENDING);
        }
        System.out.print("\n paymentStatus=> "+ this.getPaymentStatus());
        System.out.println();

    }
}
