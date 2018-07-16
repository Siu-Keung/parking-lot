package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Startup {
    public static void main(String[] args){
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.setId("1");
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.setId("2");
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy boy = new ParkingBoy(parkingLots);
        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        ParkingController parkingController = new ParkingController();
        parkingController.setBoy(boy);
        parkingController.setInputHandler(inputHandler);
        parkingController.setOutputHandler(outputHandler);
        ManagementController managementController = new ManagementController();
        managementController.setBoy(boy);
        managementController.setOutputHandler(outputHandler);
        Router router = new Router();

        router.setParkingController(parkingController);
        router.setManagementController(managementController);
        outputHandler.outputMainPage();
        while(true){
            String command = inputHandler.readNextCommand();
            router.distributeRequest(command);
        }
    }


}
