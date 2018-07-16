package com.thoughtworks.tdd;

import com.thoughtworks.controller.ManagementController;
import com.thoughtworks.controller.ParkingController;
import com.thoughtworks.exceptions.IllegalCommandException;

/**
 * @author Dylan Wei
 * @date 2018-07-16 14:28
 */
public class Router {
    private ParkingController parkingController;
    private ManagementController managementController;
    private String currentPage = "main";

    public ManagementController getManagementController() {
        return managementController;
    }

    public void setManagementController(ManagementController managementController) {
        this.managementController = managementController;
    }

    public ParkingController getParkingController() {
        return parkingController;
    }

    public void setParkingController(ParkingController parkingController) {
        this.parkingController = parkingController;
    }

    public void distributeRequest(String command) {
        switch (currentPage) {
            case "main":
                currentPage = doMain(command);
                break;
            case "parking_main":
                currentPage = doParkingMain(command);
                break;
            case "parking_park":
                currentPage = doParkingPark(command);
                break;
            case "parking_unpark":
                currentPage = doParkingUnpark(command);
                break;
            case "management_main":
                currentPage = doManagementMain(command);
                break;
            case "management_details":
                break;
            case "management_add":
                currentPage = doManagementAdd(command);
                break;
            case "management_remove":
                currentPage = doManagementRemove(command);
                break;
        }

    }

    public String doMain(String command) {
        if (command.equals("1")) {
            parkingController.mainPage();
            return "parking_main";
        } else if (command.equals("2")) {
            managementController.mainPage();
            return "management_main";
        } else {
            System.exit(0);
            return "";
        }
    }

    public String doParkingMain(String command) {
        if (command.equals("1")) {
            parkingController.parkPage();
            return "parking_park";
        } else if (command.equals("2")) {
            parkingController.unparkPage();
            return "parking_unpark";
        } else {
            throw new IllegalCommandException();
        }
    }

    public String doParkingPark(String carNo) {
        String nextPage = parkingController.handlePark(carNo);
        return nextPage;
    }

    public String doParkingUnpark(String certificateNo) {
        String nextPage = parkingController.handleUnpark(certificateNo);
        return nextPage;
    }

    public String doManagementMain(String command) {
        switch (command) {
            case "1":
                return doManagementDetails();
            case "2":
                return managementController.addParkingLotPage();
            case "3":
                return managementController.removeParkingLotPage();
            default:
                return "main";
        }
    }

    public String doManagementDetails() {
        String nextPageName = managementController
                .handleParkingLotDetailsRequest();
        return nextPageName;
    }

    public String doManagementAdd(String parkingLotInfo){
        String nextPageName = managementController.handleAddParkingLotRequesst(parkingLotInfo);
        return nextPageName;
    }

    public String doManagementRemove(String parkingLotId){
        String nextPageName = managementController.handleRemoveParkingLotRequest(parkingLotId);
        return nextPageName;
    }


}
