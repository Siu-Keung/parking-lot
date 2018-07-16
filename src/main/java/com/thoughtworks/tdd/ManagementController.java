package com.thoughtworks.tdd;

import com.thoughtworks.exceptions.ForbiddenOperationException;

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
            System.out.println("找不到分组");
        }
        String name = matcher.group(1);
        int size = Integer.valueOf(matcher.group(2));
        ParkingLot parkingLot = new ParkingLot(size);
        parkingLot.setName(name);
        boy.addParkingLot(parkingLot);
        outputHandler.send("停车场添加成功！");
        outputHandler.outputMainPage();
        return "main";
    }

    public String removeParkingLotPage(){
        outputHandler.send("请输入需要删除的被管理停车场ID:");
        return "management_remove";
    }

    public String handleRemoveParkingLotRequest(String parkingLotId){
        if(!boy.hasParkingLot(parkingLotId)){
            outputHandler.send("停车删除失败，原因：停车场不存在！");
            outputHandler.outputMainPage();
            return "main";
        }
        try {
            boy.removeParkingLot(parkingLotId);
            outputHandler.send("停车场删除成功！");
            outputHandler.outputMainPage();
        }catch (ForbiddenOperationException e){
            outputHandler.send("停车场删除失败，原因：此停车场中，依然停有汽车，无法删除！");
            outputHandler.outputMainPage();
        }
        return "main";
    }
}
