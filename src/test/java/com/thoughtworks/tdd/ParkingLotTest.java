package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

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
    public void should_throw_exception_when_parkingLot_is_full(){
        parkingLot.parkCar(mock(Car.class));
        parkingLot.parkCar(mock(Car.class));

        try {
            parkingLot.parkCar(mock(Car.class));
        }catch (NoParkingSpacesException e){
            return;
        }
        Assertions.fail("测试失败，当车位已满时应当抛出异常！");
    }

    @Test
    public void should_get_car_when_certificate_is_available(){
        Certificate certificate = this.parkingLot.parkCar(this.car);

        Car car = this.parkingLot.getCar(certificate);
        assertThat(car, is((this.car)));
    }

    @Test
    public void should_throw_exception_when_certificate_not_available(){
        Certificate certificate = this.parkingLot.parkCar(this.car);

        try {
            this.parkingLot.getCar(notAvailableCertificate);
        }catch (UnavailableCertificateException e){
            return;
        }
        Assertions.fail("测试失败，当凭证无效时应当抛异常！");
    }

    @Test
    public void should_get_available_space_is_1_given_1(){
        this.parkingLot.parkCar(this.car);
        assertThat(this.parkingLot.getAvailableSpaces(), is(1));
    }


}
