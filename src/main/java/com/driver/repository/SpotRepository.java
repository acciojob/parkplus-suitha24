package com.driver.repository;

import com.driver.model.Spot;
import com.driver.model.SpotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer>{

    @Query(value = "select * from spot where parking_lot_id =:parkingLotId and spot_type =:spotType and occupied =0 limit 1",nativeQuery = true)
    Spot findSpot(int parkingLotId, String spotType);
}
