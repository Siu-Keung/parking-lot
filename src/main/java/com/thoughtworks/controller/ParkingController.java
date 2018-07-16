package com.thoughtworks.controller;

import com.thoughtworks.domain.Car;
import com.thoughtworks.domain.Certificate;
import com.thoughtworks.domain.ParkingBoy;
import com.thoughtworks.exceptions.NoParkingSpacesException;
import com.thoughtworks.exceptions.UnavailableCertificateException;
import com.thoughtworks.tdd.InputHandler;
import com.thoughtworks.tdd.OutputHandler;

/**
 * @author Dylan Wei
 * @date 2018-07-16 10:30
 */
public class ParkingController {
    private ParkingBoy boy;
    private InputHandler inputHandler;
    private OutputHandler outputHandler;

    public ParkingBoy getBoy() {
        return this.boy;
    }

    public void setBoy(ParkingBoy boy) {
        this.boy = boy;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }

    public void setOutputHandler(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    public String mainPage(){
        outputHandler.outputParkingMainPage();
        return "main";
    }

    public String parkPage(){
        outputHandler.outputParkingParkPage();
        return "park";
    }

    public String handlePark(String carNo){
        Car car = new Car(carNo);
        Certificate certificate;
        try {
            certificate = this.boy.park(car);
            outputHandler.outputParkingParkSuccessfully(certificate.getId());
            outputHandler.outputMainPage();
            return "main";
        }catch (NoParkingSpacesException e){
            outputHandler.outputParkingParkingLotsIsFull();
            outputHandler.outputMainPage();
            return "main";
        }
    }

    public String unparkPage(){
        outputHandler.outputParkingUnparkPage();
        return "unpark";
    }

    public String handleUnpark(String certificateNo){
        Certificate certificate = new Certificate(certificateNo);
        Car car = null;
        try {
            car = this.boy.unpark(certificate);
            outputHandler.outputParkingUnparkSuccessfully(car.getCarNumber());
            outputHandler.outputMainPage();
            return "main";
        }catch (UnavailableCertificateException e){
            outputHandler.outputUnavailableCertificate();
            outputHandler.outputMainPage();
            return "main";
        }
    }

}
