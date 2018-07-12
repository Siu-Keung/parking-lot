package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Dylan Wei
 * @date 2018-07-12 10:59
 */
public class ParkingBoyTest {

    @Test
    public void should_get_certificate_when_has_spaces() {
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        Car car = new Car("123456");
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        Certificate certificate = parkingBoy.park(car);

        assertThat(certificate, notNullValue());
    }

    @Test
    public void should_get_null_when_parking_lot_has_no_spaces() {
        ParkingLot parkingLot = new ParkingLot(0);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        Car car = new Car("123456");
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        Certificate certificate = parkingBoy.park(car);

        assertThat(certificate, nullValue());
    }

    @Test
    public void should_car_be_parked_in_first_parkinglot_when_park(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car("123456");

        parkingBoy.park(car);

        assertThat(parkingLot1.getAvailableSpaces(), is(0));
        assertThat(parkingLot2.getAvailableSpaces(), is(1));
    }

    @Test
    public void should_get_car_when_certificate_is_available(){
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        Car car = new Car("123456");
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        Certificate certificate = parkingBoy.park(car);
        Car car1 = parkingBoy.unpark(certificate);

        assertThat(car1, is(car));
    }

    @Test
    public void should_get_null_when_certificate_not_available(){
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        Car car = new Car("123456");
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        Certificate fakeCertificate = new Certificate("46846848", car, parkingLot);
        Car car1 = parkingBoy.unpark(fakeCertificate);

        assertThat(car1, nullValue());
    }

    @Test
    public void should_get_true_when_parking_lot_not_full(){
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        boolean canPark = parkingBoy.canPark();

        assertThat(canPark, is(true));
    }

    @Test
    public void should_get_false_when_parking_lot_not_full(){
        ParkingLot parkingLot = new ParkingLot(0);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        boolean canPark = parkingBoy.canPark();

        assertThat(canPark, is(false));
    }

}
