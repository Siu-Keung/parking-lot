package com.thoughtworks.controller;

import com.thoughtworks.domain.ParkingBoy;
import com.thoughtworks.domain.ParkingLot;
import com.thoughtworks.exceptions.ForbiddenOperationException;
import com.thoughtworks.exceptions.IllegalCommandException;
import com.thoughtworks.tdd.OutputHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dylan Wei
 * @date 2018-07-16 15:36
 */
public class ManagementController {
    private ParkingBoy boy;
    private OutputHandler outputHandler;

    public ParkingBoy getBoy() {
        return boy;
    }

    public void setBoy(ParkingBoy boy) {
        this.boy = boy;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }

    public void setOutputHandler(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    public String mainPage(){
        outputHandler.send("1.查看停车场详情\n2.添加停车场\n3.删除停车场\n");
        return "management_main";
    }

    public String handleParkingLotDetailsRequest(){
        outputHandler.outputParkingLotsDetailsPage(boy.getParkingLots());
        outputHandler.outputMainPage();
        return "main";
    }

    public String addParkingLotPage(){
        outputHandler.send("请输入您要添加的停车场信息（格式为：名称，车位）：");
        return "management_add";
    }

    public String handleAddParkingLotRequesst(String parkingLotInfo){
        String regex = "(.*)[,，](.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(parkingLotInfo);
        if(!matcher.find()){
            outputHandler.outputAddFaild();
            outputHandler.outputMainPage();
            return "main";
        }
        String name = matcher.group(1);
        int size = Integer.valueOf(matcher.group(2));
        ParkingLot parkingLot = new ParkingLot(size);
        parkingLot.setName(name);
        boy.addParkingLot(parkingLot);
        outputHandler.outputAddParkingLotSuccessfully();
        outputHandler.outputMainPage();
        return "main";
    }

    public String removeParkingLotPage(){
        outputHandler.outputEnterParkingLotId();
        return "management_remove";
    }

    public String handleRemoveParkingLotRequest(String parkingLotId){
        if(!boy.hasParkingLot(parkingLotId)){
            outputHandler.outputRemoveParkingLotFailed("停车场不存在！");
            outputHandler.outputMainPage();
            return "main";
        }
        try {
            boy.removeParkingLot(parkingLotId);
            outputHandler.outputRemoveParkingLotSuccessfully();
            outputHandler.outputMainPage();
        }catch (ForbiddenOperationException e){
            outputHandler.outputRemoveParkingLotFailed("此停车场中，依然停有汽车，无法删除！");
            outputHandler.outputMainPage();
        }
        return "main";
    }
}
