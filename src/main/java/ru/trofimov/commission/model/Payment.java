package ru.trofimov.commission.model;

import java.util.Date;

public class Payment {

    private long id;

    private long phoneNumber;

    private long paymentAmount;

    private Date date;

    private String comment;

    public Payment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(long paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", phoneNumber=" + phoneNumber +
                ", paymentAmount=" + paymentAmount +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                '}';
    }
}
