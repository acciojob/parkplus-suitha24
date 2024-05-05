package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Reservation;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//import static com.driver.model.SpotType.*;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;

    @Autowired
    ReservationRepository reservationRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {

        ParkingLot newParkingLot=new ParkingLot();
        newParkingLot.setAddress(address);
        newParkingLot.setName(name);
        parkingLotRepository1.save(newParkingLot);
        //return parkingLotRepository1.findParkingLotById(newParkingLot.getId());
        return newParkingLot;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        Spot newSpot=new Spot();

        //find spot type
        if(numberOfWheels<=2)
            newSpot.setSpotType(SpotType.TWO_WHEELER);
        else if (numberOfWheels>2&&numberOfWheels<=4)
            newSpot.setSpotType(SpotType.FOUR_WHEELER);
        else if(numberOfWheels>4)
            newSpot.setSpotType(SpotType.OTHERS);

        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();
        if(parkingLot==null)
            return null;

        //if (parkingLot!=null){
        newSpot.setParkingLot(parkingLot);
        newSpot.setOccupied(false);
        newSpot.setPricePerHour(pricePerHour);
        parkingLot.getSpotList().add(newSpot);
        parkingLotRepository1.save(parkingLot);

       // spotRepository1.save(newSpot);

//        List<Reservation> reservation= reservationRepository1.findReservationBySpotId(newSpot.getId());
//        newSpot.setReservationList(reservation);
//        spotRepository1.save(newSpot);

        //}
        return newSpot;

    }

    @Override
    public void deleteSpot(int spotId) {
        Spot deletedSpot=spotRepository1.findSpotById(spotId);
                //spotRepository1.findById(spotId).get();
        spotRepository1.delete(deletedSpot);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        Spot updatedSpot=spotRepository1.findSpotById(spotId);
                //spotRepository1.findById(spotId).get();
        updatedSpot.setPricePerHour(pricePerHour);
        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();
        List<Spot> spotList=parkingLot.getSpotList();
        for(Spot spot:spotList){
            if(spot.getId()==spotId){
                spot=updatedSpot;
            }
        }
        parkingLotRepository1.save(parkingLot);
        spotRepository1.save(updatedSpot);
        return updatedSpot;
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);
    }
}
