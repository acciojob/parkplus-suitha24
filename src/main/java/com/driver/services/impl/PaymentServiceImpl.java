package com.driver.services.impl;

import com.driver.exception.InsufficientAmount;
import com.driver.exception.PaymentModeNotDetected;
import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.model.Spot;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
        Payment payment=new Payment();

        Reservation reservation= reservationRepository2.findById(reservationId).get();
        //reservationRepository2.findReservationById(reservationId);

        //calculate price
        int totalHrs=reservation.getNumberOfHours();
        int pricePerHr=reservation.getSpot().getPricePerHour();

        if(amountSent<totalHrs*pricePerHr){
           // return null;
           // throw new Exception("Insufficient Amount");
            throw new InsufficientAmount("Insufficient Amount");

        }


//        if(mode.equals("CASH")==false || mode.equals("CARD")==false || mode.equals("UPI")==false)
//            throw new Exception("Payment mode not detected");

        mode=mode.toUpperCase();
        //set payment mode
        if(mode.equals("CARD"))
            payment.setPaymentMode(PaymentMode.CARD);
        else if(mode.equals("CASH"))
            payment.setPaymentMode(PaymentMode.CASH);
        else if(mode.equals("UPI"))
            payment.setPaymentMode(PaymentMode.UPI);
        else
            throw new PaymentModeNotDetected("Payment mode not detected");

        payment.setPaymentCompleted(true);
        payment.setReservation(reservation);

        reservation.setPayment(payment);
        reservationRepository2.save(reservation);
       // paymentRepository2.save(payment);

        return payment;

    }
}
