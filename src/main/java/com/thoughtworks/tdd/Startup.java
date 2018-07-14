package com.thoughtworks.tdd;

import com.thoughtworks.exceptions.IllegalCommandException;
import com.thoughtworks.exceptions.NoParkingSpacesException;
import com.thoughtworks.exceptions.UnavailableCertificateException;
import java.util.Arrays;
import java.util.List;

public class Startup {
    public static void main(String[] args){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        ParkingBoy boy = new ParkingBoy(parkingLots);
        InteractionHandler handler = new InteractionHandler();
        while(true){
            try {
                int choice = handler.showMainScreenAndGetChoice();
                if(choice == 1){
                    handlePark(boy, handler);
                }else{
                    handleUnpark(boy, handler);
                }
            }catch (IllegalCommandException e){
                handler.alertIllegalCommand();
                continue;
            }

        }
    }

    private static void handleUnpark(ParkingBoy boy, InteractionHandler handler) {
        String certificateNo = handler.displayPromptAndGetCertificateNo();
        Car car = null;
        try {
            car = boy.unpark(new Certificate(certificateNo));
            handler.displayUnparkSuccessfully(car.getCarNumber());
        }catch (UnavailableCertificateException e){
            handler.alertNotAvailableCertificate();
        }
    }

    private static void handlePark(ParkingBoy boy, InteractionHandler handler){
        String carNo = handler.displayPromptAndGetCarNum();
        Car car = new Car(carNo);
        Certificate certificate;
        try {
            certificate = boy.park(car);
            handler.displayParkSuccessfully(certificate.getId());
        }catch (NoParkingSpacesException e){
            handler.alertAllParkingLotIsFull();
        }
    }

}
