package com.driver.services.impl;

import com.driver.exception.CannotMakeReservation;
import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels)  {
        //User user=userRepository3.findById(userId).get();
        User user= userRepository3.findById(userId).get();
        if(user==null)
            throw new CannotMakeReservation("Cannot make reservation");

        //ParkingLot parkingLot=parkingLotRepository3.findById(parkingLotId).get();
        ParkingLot parkingLot= parkingLotRepository3.findParkingLotById(parkingLotId);
        if(parkingLot==null)
            throw new CannotMakeReservation("Cannot make reservation");

        Spot checkSpot=new Spot();
        String spotType;
        if (numberOfWheels==2){
            spotType = "TWO_WHEELER";
             checkSpot=spotRepository3.findSpotforTwoWheeler(parkingLotId);
        }

        else if(numberOfWheels==4){
            spotType = "FOUR_WHEELER";
             checkSpot=spotRepository3.findSpotforFourWheeler(parkingLotId);
        }

        else {
            spotType = "OTHERS";
             checkSpot=spotRepository3.findSpotforOthers(parkingLotId);
        }

        if (checkSpot==null)
            throw new CannotMakeReservation("Cannot make reservation");

       // else {
            checkSpot.setOccupied(true);
            Reservation newReservation=new Reservation();
            newReservation.setSpot(checkSpot);
            newReservation.setUser(user);
            newReservation.setNumberOfHours(timeInHours);

            reservationRepository3.save(newReservation);

            return newReservation;
        //}


    }
}
