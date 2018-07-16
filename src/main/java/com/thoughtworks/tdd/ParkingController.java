package com.thoughtworks.tdd;

import com.thoughtworks.exceptions.NoParkingSpacesException;
import com.thoughtworks.exceptions.UnavailableCertificateException;

/**
 * @author Dylan Wei
 * @date 2018-07-16 10:30
 */
public class ParkingController {
    private  ParkingBoy boy;
    private InputHandler inputHandler;
    private OutputHandler outputHandler;

    public ParkingBoy getBoy() {
        return boy;
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

    /**
     * -------------------------------------------------------------------------------------------------------------------------------
     */

    public String mainPage(){
        outputHandler.send("1. 停车\n2. 取车\n请输入您要进行的操作：");
        return "main";
    }

    public String parkPage(){
        outputHandler.send("请输入车牌号：");
        return "park";
    }

    public String handlePark(String carNo){
        Car car = new Car(carNo);
        Certificate certificate;
        try {
            certificate = boy.park(car);
            outputHandler.send("停车成功，您的小票是：\n" + certificate.getId());
            outputHandler.outputMainPage();
            return "main";
        }catch (NoParkingSpacesException e){
            outputHandler.send("车已停满，请晚点再来");
            outputHandler.outputMainPage();
            return "main";
        }
    }

    public String unparkPage(){
        outputHandler.send("请输入小票编号：");
        return "unpark";
    }

    public String handleUnpark(String certificateNo){
        Certificate certificate = new Certificate(certificateNo);
        Car car = null;
        try {
            car = boy.unpark(certificate);
            outputHandler.send("车已取出，您的车牌号是: " + car.getCarNumber());
            outputHandler.outputMainPage();
            return "main";
        }catch (UnavailableCertificateException e){
            outputHandler.send("非法小票，无法取出车，请查证后再输");
            outputHandler.outputMainPage();
            return "main";
        }
    }

}
