package com.driver.repository;

import com.driver.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer>{

    @Query(value = "select * from parking_lot where id=:id",nativeQuery = true)
    ParkingLot findParkingLotById(int id);
}
