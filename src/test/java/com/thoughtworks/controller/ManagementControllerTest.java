package com.thoughtworks.controller;

import com.thoughtworks.domain.ParkingBoy;
import com.thoughtworks.domain.ParkingLot;
import com.thoughtworks.exceptions.ForbiddenOperationException;
import com.thoughtworks.tdd.OutputHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Dylan Wei
 * @date 2018-07-16 20:53
 */
class ManagementControllerTest {
    private ManagementController controller;

    @BeforeEach
    void setUp() {
        controller = new ManagementController();
    }

    @Test
    public void should_add_parking_lot_successfully_when_input_valid(){
        ParkingBoy boy = mock(ParkingBoy.class);
        OutputHandler outputHandler = mock(OutputHandler.class);
        this.controller.setBoy(boy);
        this.controller.setOutputHandler(outputHandler);

        this.controller.handleAddParkingLotRequesst("西停车场，13");

        verify(boy).addParkingLot(any());
    }

    @Test
    public void should_add_parking_lot_failed_when_inptu_invalid(){
        ParkingBoy boy = mock(ParkingBoy.class);
        OutputHandler outputHandler = mock(OutputHandler.class);
        this.controller.setBoy(boy);
        this.controller.setOutputHandler(outputHandler);

        this.controller.handleAddParkingLotRequesst("西停车13");

        verify(outputHandler).outputAddFaild();
        verify(outputHandler).outputMainPage();
    }

    @Test
    public void should_remove_successfully_when_id_valid(){
        ParkingBoy boy = mock(ParkingBoy.class);
        OutputHandler handler = mock(OutputHandler.class);
        this.controller.setBoy(boy);
        this.controller.setOutputHandler(handler);

        when(boy.hasParkingLot(anyString())).thenReturn(true);

        this.controller.handleRemoveParkingLotRequest("fadfafdf");

        verify(handler).outputRemoveParkingLotSuccessfully();
        verify(handler).outputMainPage();
    }

    @Test
    public void should_remove_failed_when_parkingLot_id_invalid(){
        ParkingBoy boy = mock(ParkingBoy.class);
        OutputHandler handler = mock(OutputHandler.class);
        this.controller.setBoy(boy);
        this.controller.setOutputHandler(handler);

        when(boy.hasParkingLot(anyString())).thenReturn(false);

        this.controller.handleRemoveParkingLotRequest("fadfafdf");

        verify(handler).outputRemoveParkingLotFailed(any());
        verify(handler).outputMainPage();
    }

    @Test
    public void should_remove_failed_when_parking_lot_not_empty(){
        ParkingBoy boy = mock(ParkingBoy.class);
        OutputHandler handler = mock(OutputHandler.class);
        this.controller.setBoy(boy);
        this.controller.setOutputHandler(handler);

        when(boy.hasParkingLot(anyString())).thenReturn(true);
        doThrow(ForbiddenOperationException.class).when(boy).removeParkingLot(any());

        this.controller.handleRemoveParkingLotRequest("1");

        verify(handler).outputRemoveParkingLotFailed("此停车场中，依然停有汽车，无法删除！");
        verify(handler).outputMainPage();
    }

}