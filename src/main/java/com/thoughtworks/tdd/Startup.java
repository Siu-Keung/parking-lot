package com.thoughtworks.tdd;

import com.thoughtworks.controller.ManagementController;
import com.thoughtworks.controller.ParkingController;
import com.thoughtworks.domain.ParkingBoy;
import com.thoughtworks.domain.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class Startup {
    public static void main(String[] args){
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.setId("1");
        parkingLot1.setName("西南停车场");
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.setId("2");
        parkingLot2.setName("东北停车场");
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
