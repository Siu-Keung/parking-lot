package com.thoughtworks.tdd;

import com.thoughtworks.domain.ParkingLot;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        System.setOut(ps);
        handler.outputParkingLotsDetailsPage(lots);

        assertThat(new String(out.toByteArray()), is("|停车场ID|名称|车位|已停车辆|剩余车位|\n" +
                "======================================\n" +
                "|001|西南停车场|10(个)|7(辆)|3(个)|\n" +
                "|002|南方软件园停车场|8(个)|2(辆)|6(个)|\n" +
                "\n" +
                "总车位：18(个)\n" +
                "停车总量：9(辆)\n" +
                "总剩余车位：9(个)\r\n"));
    }

}
