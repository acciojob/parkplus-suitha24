package com.driver.repository;

import com.driver.model.Spot;
import com.driver.model.SpotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer>{

    @Query(value = "select * from spot where id=:id",nativeQuery = true)
    Spot findSpotById(int id);

    @Query(value = "select * from spot where parking_lot_id =:parkingLotId and spot_type ='FOUR_WHEELER' or spot_type ='OTHERS' and occupied =0 order by price_per_hour limit 1",nativeQuery = true)
    Spot findSpotforFourWheeler(int parkingLotId);

    @Query(value = "select * from spot where parking_lot_id =:parkingLotId and spot_type ='OTHERS' and occupied =0 order by price_per_hour limit 1",nativeQuery = true)
    Spot findSpotforOthers(int parkingLotId);

    @Query(value = "select * from spot where parking_lot_id =:parkingLotId and spot_type ='TWO_WHEELER' or spot_type ='FOUR_WHEELER' or spot_type ='OTHERS' and occupied =0 order by price_per_hour limit 1",nativeQuery = true)
    Spot findSpotforTwoWheeler(int parkingLotId);
}
