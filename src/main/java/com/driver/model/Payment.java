package com.driver.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn
   // @JsonIgnore
    private Reservation reservation;

    boolean paymentCompleted;
    @Enumerated(EnumType.STRING)
    PaymentMode paymentMode;

    public Payment() {
    }

    public Payment(int id, Reservation reservation, boolean paymentCompleted, PaymentMode paymentMode) {
        this.id = id;
        this.reservation = reservation;
        this.paymentCompleted = paymentCompleted;
        this.paymentMode = paymentMode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public boolean isPaymentCompleted() {
        return paymentCompleted;
    }

    public void setPaymentCompleted(boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }
}