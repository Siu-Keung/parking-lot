package com.thoughtworks.tdd;

/**
 * @author Dylan Wei
 * @date 2018-07-11 14:12
 */
public class Certificate {
    private String id;
    private Car car;
    private ParkingLot parkingLot;

    public Certificate(String id, Car car, ParkingLot parkingLot) {
        this.id = id;
        this.car = car;
        this.parkingLot = parkingLot;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id='" + id + '\'' +
                ", car=" + car +
                ", parkingLot=" + parkingLot +
                '}';
    }
}
