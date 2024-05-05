package com.driver.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn
    //@JsonIgnore
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "spot",cascade = CascadeType.ALL)
  //  @JsonIgnore
    List<Reservation> reservationList=new ArrayList<>();

    @Enumerated(EnumType.STRING)
    SpotType spotType;
    private Integer pricePerHour;
    private Boolean occupied;

    public Spot() {
    }

    public Spot(int id, ParkingLot parkingLot, List<Reservation> reservationList, SpotType spotType, Integer pricePerHour, Boolean occupied) {
        this.id = id;
        this.parkingLot = parkingLot;
        this.reservationList = reservationList;
        this.spotType = spotType;
        this.pricePerHour = pricePerHour;
        this.occupied = occupied;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public Integer getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Integer pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }
}