package com.driver.services.impl;

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
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        User user=userRepository3.findById(userId).get();
        if(user.getName()==null)
            throw new Exception("Cannot make reservation");
        ParkingLot parkingLot=parkingLotRepository3.findById(parkingLotId).get();
        if(parkingLot.getName()==null)
            throw new Exception("Cannot make reservation");

        String spotType;
        if (numberOfWheels==2)
            spotType = "TWO_WHEELER";
        else if(numberOfWheels==4)
            spotType = "FOUR_WHEELER";
        else //if(numberOfWheels>4)
            spotType = "OTHERS";

        Spot checkSpot=spotRepository3.findSpot(parkingLotId,spotType);
        if (checkSpot==null)
            throw new Exception("Cannot make reservation");

        else {
            checkSpot.setOccupied(true);
            Reservation newReservation=new Reservation();
            newReservation.setSpot(checkSpot);
            newReservation.setUser(user);
            newReservation.setNumberOfHours(timeInHours);

            reservationRepository3.save(newReservation);

            return newReservation;
        }


    }
}
