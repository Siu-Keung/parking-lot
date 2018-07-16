//package com.thoughtworks.tdd;
//
//import org.junit.jupiter.api.Test;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
///**
// * @author Dylan Wei
// * @date 2018-07-16 10:55
// */
//public class ParkingControllerTest {
//
//    @Test
//    public void should_call_park_successfully_method(){
//        ParkingBoy boy = mock(ParkingBoy.class);
//        when(boy.park(any())).thenReturn(mock(Certificate.class));
//        InteractionHandler handler = mock(InteractionHandler.class);
//        when(handler.displayPromptAndGetCarNum()).thenReturn("粤C8795");
//        ParkingController controller = new ParkingController(boy, handler);
//
//        controller.handlePark(boy, handler);
//
//        verify(handler).displayParkSuccessfully(any());
//    }
//
//    @Test
//    public void should_call_unpark_successfully_method(){
//        InteractionHandler handler = mock(InteractionHandler.class);
//        when(handler.displayPromptAndGetCertificateNo()).thenReturn("464864654");
//        ParkingBoy boy = mock(ParkingBoy.class);
//        when(boy.unpark(any())).thenReturn(mock(Car.class));
//        ParkingController controller = new ParkingController(boy, handler);
//
//        controller.handleUnpark(boy, handler);
//
//        verify(handler).displayUnparkSuccessfully(any());
//    }
//
//
//}
