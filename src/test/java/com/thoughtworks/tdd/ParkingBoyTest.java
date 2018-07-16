package com.thoughtworks.tdd;

import com.thoughtworks.domain.Car;
import com.thoughtworks.domain.Certificate;
import com.thoughtworks.domain.ParkingBoy;
import com.thoughtworks.domain.ParkingLot;
import com.thoughtworks.exceptions.NoParkingSpacesException;
import com.thoughtworks.exceptions.UnavailableCertificateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Dylan Wei
 * @date 2018-07-12 10:59
 */
public class ParkingBoyTest {

    @Test
    public void should_get_certificate_when_has_spaces() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.getAvailableSpaces()).thenReturn(10);
        when(parkingLot.parkCar(any())).thenReturn(mock(Certificate.class));
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        Car car = mock(Car.class);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        Certificate certificate = parkingBoy.park(car);

        assertThat(certificate, notNullValue());
    }

    @Test
    public void should_throw_exception_when_all_parking_lot_has_no_spaces() {
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.getAvailableSpaces()).thenReturn(0);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        try {
            Certificate certificate = parkingBoy.park(mock(Car.class));
        }catch (NoParkingSpacesException e){
            return;
        }
        Assertions.fail("当所有停车场都没有车位时，应当抛出异常！");
    }

    @Test
    public void should_get_car_when_certificate_is_available(){
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        Car car = mock(Car.class);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        Certificate certificate = parkingBoy.park(car);
        Car car1 = parkingBoy.unpark(certificate);

        assertThat(car1, is(car));
    }

    @Test
    public void should_throw_exception_when_certificate_not_available(){
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        Car car = mock(Car.class);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        Certificate fakeCertificate = new Certificate("46846848", car);

        try {
            parkingBoy.unpark(fakeCertificate);
        }catch (UnavailableCertificateException e){
            return;
        }
        Assertions.fail("当凭证无效时，应当抛出异常！");
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
    public void should_get_false_when_parking_lot_full(){
        ParkingLot parkingLot = new ParkingLot(0);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        boolean canPark = parkingBoy.canPark();

        assertThat(canPark, is(false));
    }

    @Test
    public void should_cars_be_parked_into_2_parkinglots_orderly_when_park_2_cars(){
        Car car = mock(Car.class);
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        Certificate certificate1 = mock(Certificate.class);
        when(parkingLot1.getAvailableSpaces()).thenReturn(1, 0);
        when(parkingLot1.parkCar(car)).thenReturn(certificate1);
        when(certificate1.getId()).thenReturn("hfawhfuwhefjunksnfkjew");
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        Certificate certificate2 = mock(Certificate.class);
        when(parkingLot2.getAvailableSpaces()).thenReturn(1, 0);
        when(parkingLot2.parkCar(car)).thenReturn(certificate2);
        when(certificate2.getId()).thenReturn("fajiowjofijewafiajfewffaew");
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        ParkingBoy boy = new ParkingBoy(parkingLots);
        InOrder inOrder = Mockito.inOrder(parkingLot1, parkingLot2);

        boy.park(car);
        boy.park(car);

        inOrder.verify(parkingLot1).parkCar(car);
        inOrder.verify(parkingLot2).parkCar(car);
    }

}
