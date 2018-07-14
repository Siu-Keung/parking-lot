package com.thoughtworks.tdd;

import com.thoughtworks.exceptions.NoParkingSpacesException;
import com.thoughtworks.exceptions.UnavailableCertificateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dylan Wei
 * @date 2018-07-12 11:09
 */
public class ParkingBoy {
    private List<ParkingLot> parkingLots;
    private Map<String, ParkingLot> certificateNoPrakingLotMap;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
        this.certificateNoPrakingLotMap = new HashMap<>();
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        this.certificateNoPrakingLotMap = new HashMap<>();
    }

    public void addParkingLot(ParkingLot parkingLot){
        this.parkingLots.add(parkingLot);
    }

    public Certificate park(Car car) {
        ParkingLot notFullParkingLot = this.getFirstNotFullParkingLot();
        if(notFullParkingLot == null)
            throw new NoParkingSpacesException();
        Certificate certificate = notFullParkingLot.parkCar(car);
        this.certificateNoPrakingLotMap.put(certificate.getId(), notFullParkingLot);
        return certificate;
    }

    private ParkingLot getFirstNotFullParkingLot(){
        for (ParkingLot parkingLot : parkingLots) {
            if(parkingLot.getAvailableSpaces() > 0)
                return parkingLot;
        }
        return null;
    }

    public Car unpark(Certificate certificate) {
        ParkingLot parkingLot = this.certificateNoPrakingLotMap.remove(certificate.getId());
        if(parkingLot == null)
            throw new UnavailableCertificateException();
        return parkingLot.getCar(certificate);
    }

    public boolean canPark() {
        boolean canPark = this.getFirstNotFullParkingLot() == null ? false : true;
        return canPark;
    }
}
