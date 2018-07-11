package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Dylan Wei
 * @date 2018-07-11 14:13
 */
public class ParkingLot {
    public Map<String, Car> parkingSpaces;
    private Integer size;

    public ParkingLot(Integer size) {
        this.size = size;
        this.parkingSpaces = new HashMap<>(size);
    }

    public Certificate parkCar(Car car){
        if(this.parkingSpaces.size() == this.size)
            return null;
        Certificate certificate = this.generateCertificate(car);
        this.parkingSpaces.put(certificate.getId(), car);
        return certificate;
    }

    private Certificate generateCertificate(Car car){
        String id = UUID.randomUUID().toString();
        Certificate certificate = new Certificate(id, car, this);
        return certificate;
    }

    public Car getCar(Certificate certificate){
        if(!certificateIsAvailable(certificate))
            return null;
        Car car = this.parkingSpaces.get(certificate.getId());
        this.parkingSpaces.remove(certificate.getId());
        return car;
    }

    private boolean certificateIsAvailable(Certificate certificate){
        return this.parkingSpaces.containsKey(certificate.getId()) 
                && certificate.getParkingLot() == this;
    }


}
