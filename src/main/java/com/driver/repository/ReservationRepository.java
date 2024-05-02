package com.driver.repository;

import com.driver.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

    @Query(value = "select * from reservation where id=:id",nativeQuery = true)
    Reservation findReservationById(int id);

    @Query(value = "select * from reservation where spot_id=:id",nativeQuery = true)
    List<Reservation> findReservationBySpotId(int id);

    @Query(value = "select * from reservation where user_id=:id",nativeQuery = true)
    List<Reservation> findReservationByUserId(int id);
}
