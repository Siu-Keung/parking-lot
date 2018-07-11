package com.thoughtworks.tdd;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Dylan Wei
 * @date 2018-07-11 14:26
 */
public class ParkingLotTest {
    private Car car;
    private ParkingLot parkingLot;
    private Certificate notAvailableCertificate;

    @BeforeEach
    private void beforeAll(){
        this.car = new Car("123456");
        this.parkingLot = new ParkingLot(2);
        this.notAvailableCertificate = new Certificate(
                UUID.randomUUID().toString(), new Car("9999999"), new ParkingLot(10));
    }

    @Test
    public void should_get_Car_when_park(){
        String carNum = parkingLot.parkCar(this.car).getCar().getCarNumber();
        assertThat(carNum, is("123456"));
    }

    @Test
    public void should_get_null_when_lot_is_full(){
        parkingLot.parkCar(new Car("111111"));
        parkingLot.parkCar(new Car("222222"));

        Certificate certificate = parkingLot.parkCar(this.car);
        assertThat(certificate, nullValue());

    }

    @Test
    public void should_get_car_when_certificate_is_available(){
        Certificate certificate = this.parkingLot.parkCar(this.car);

        Car car = this.parkingLot.getCar(certificate);
        assertThat(car, is((this.car)));
    }

    @Test
    public void should_get_null_when_certificate_not_available(){
        Certificate certificate = this.parkingLot.parkCar(this.car);

        Car car = this.parkingLot.getCar(notAvailableCertificate);
        assertThat(car, nullValue());
    }

    @Test
    public void should_get_1_given_1(){
        this.parkingLot.parkCar(this.car);
        assertThat(this.parkingLot.getAvailableSpaces(), is(1));

    }

}
