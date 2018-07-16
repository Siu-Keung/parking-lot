package com.thoughtworks.controller;

import com.thoughtworks.domain.Car;
import com.thoughtworks.domain.Certificate;
import com.thoughtworks.domain.ParkingBoy;
import com.thoughtworks.exceptions.NoParkingSpacesException;
import com.thoughtworks.exceptions.UnavailableCertificateException;
import com.thoughtworks.tdd.OutputHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Dylan Wei
 * @date 2018-07-16 20:27
 */
class ParkingControllerTest {
    private ParkingController controller;

    @BeforeEach
    private void init(){
        this.controller = new ParkingController();
    }

    @Test
    public void should_park_successfully_when_not_full() {
        ParkingBoy boy = mock(ParkingBoy.class);
        OutputHandler outputHandler = mock(OutputHandler.class);
        Car car = mock(Car.class);
        Certificate certificate = mock(Certificate.class);
        this.controller.setBoy(boy);
        this.controller.setOutputHandler(outputHandler);

        when(boy.park(any())).thenReturn(certificate);
        when(certificate.getId()).thenReturn("afafafda");

        this.controller.handlePark("abcdefg");

        verify(outputHandler).outputParkingParkSuccessfully(any());
        verify(outputHandler).outputMainPage();
    }

    @Test
    public void should_output_park_unsuccessfully_when_is_full(){
        ParkingBoy boy = mock(ParkingBoy.class);
        OutputHandler outputHandler = mock(OutputHandler.class);
        this.controller.setBoy(boy);
        this.controller.setOutputHandler(outputHandler);

        when(boy.park(any())).thenThrow(new NoParkingSpacesException());

        controller.handlePark("fafadfafdafaf");

        verify(outputHandler).outputParkingParkingLotsIsFull();
        verify(outputHandler).outputMainPage();
    }

    @Test
    public void should_output_unpark_successfully_when_certificationNo_available(){
        ParkingBoy boy = mock(ParkingBoy.class);
        OutputHandler outputHandler = mock(OutputHandler.class);
        Car car = mock(Car.class);
        this.controller.setBoy(boy);
        this.controller.setOutputHandler(outputHandler);

        when(boy.unpark(any())).thenReturn(car);
        when(car.getCarNumber()).thenReturn("4684654");

        controller.handleUnpark("fewfaefa");

        verify(outputHandler).outputParkingUnparkSuccessfully(any());
        verify(outputHandler).outputMainPage();
    }

    @Test
    public void should_output_unsuccessfully_when_certificateNo_not_available(){
        ParkingBoy boy = mock(ParkingBoy.class);
        OutputHandler outputHandler = mock(OutputHandler.class);
        Car car = mock(Car.class);
        this.controller.setBoy(boy);
        this.controller.setOutputHandler(outputHandler);

        when(boy.unpark(any())).thenThrow(new UnavailableCertificateException());

        controller.handleUnpark("fewfaefa");

        verify(outputHandler).outputUnavailableCertificate();
        verify(outputHandler).outputMainPage();
    }
}