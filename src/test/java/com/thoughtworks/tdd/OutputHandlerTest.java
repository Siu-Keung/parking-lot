package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Dylan Wei
 * @date 2018-07-16 16:07
 */
public class OutputHandlerTest {

    @Test
    public void should_output_parking_lot_details(){
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        when(parkingLot1.getId()).thenReturn("001");
        when(parkingLot1.getName()).thenReturn("西南停车场");
        when(parkingLot1.getSize()).thenReturn(10);
        when(parkingLot1.getAvailableSpaces()).thenReturn(3);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        when(parkingLot2.getId()).thenReturn("002");
        when(parkingLot2.getName()).thenReturn("南方软件园停车场");
        when(parkingLot2.getSize()).thenReturn(8);
        when(parkingLot2.getAvailableSpaces()).thenReturn(6);
        List<ParkingLot> lots = Arrays.asList(parkingLot1, parkingLot2);
        OutputHandler handler = new OutputHandler();


        handler.outputParkingLotsDetailsPage(lots);
    }

}
