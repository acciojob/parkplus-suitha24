package com.driver.repository;

import com.driver.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

    @Query(value = "select * from reservation where id=:id",nativeQuery = true)
    Reservation findReservationById(int id);

}
