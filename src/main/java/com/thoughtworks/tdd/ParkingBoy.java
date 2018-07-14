package com.thoughtworks.tdd;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dylan Wei
 * @date 2018-07-12 11:09
 */
public class ParkingBoy {
    private List<ParkingLot> parkingLots;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public void addParkingLot(ParkingLot parkingLot){
        this.parkingLots.add(parkingLot);
    }

    public Certificate park(Car car) {
        ParkingLot notFullParkingLot = this.getFirstNotFullParkingLot();
        if(notFullParkingLot == null)
            throw new NoParkingSpacesException();
        return notFullParkingLot.parkCar(car);
    }

    private ParkingLot getFirstNotFullParkingLot(){
        for (ParkingLot parkingLot : parkingLots) {
            if(parkingLot.getAvailableSpaces() > 0)
                return parkingLot;
        }
        return null;
    }

    public Car unpark(Certificate certificate) {
        return certificate.getParkingLot().getCar(certificate);
    }

    public boolean canPark() {
        boolean canPark = this.getFirstNotFullParkingLot() == null ? false : true;
        return canPark;
    }
}
