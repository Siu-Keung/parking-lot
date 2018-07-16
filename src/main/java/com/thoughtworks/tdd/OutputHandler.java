package com.thoughtworks.tdd;

import com.thoughtworks.domain.ParkingLot;

import java.util.List;

/**
 * @author Dylan Wei
 * @date 2018-07-16 14:43
 */
public class OutputHandler {


    public void send(String message){
        System.out.println(message);
    }

    public void outputMainPage(){
        System.out.print("1.停车服务\n2.停车场管理\n请输入您要进入的页面：");
    }

    public void outputParkingMainPage(){
        this.send("1. 停车\n2. 取车\n请输入您要进行的操作：");
    }

    public void outputParkingParkPage(){
        this.send("请输入车牌号：");
    }

    public void outputParkingUnparkPage(){
        this.send("请输入小票编号：");
    }

    public void outputParkingParkSuccessfully(String certificateNo){
        this.send("停车成功，您的小票是：" + certificateNo);
    }

    public void outputParkingParkingLotsIsFull(){
        this.send("车已停满，请晚点再来");
    }

    public void outputParkingUnparkSuccessfully(String carNo){
        this.send("车已取出，您的车牌号是: " + carNo);
    }

    public void outputUnavailableCertificate(){
        this.send("非法小票，无法取出车，请查证后再输");
    }

    public void outputParkingLotsDetailsPage(List<ParkingLot> parkingLots){
        int total = 0;
        int used = 0;
        int left = 0;

        String output = "|停车场ID|名称|车位|已停车辆|剩余车位|";
        output += "\n======================================";
        for (ParkingLot parkingLot : parkingLots) {
            total += parkingLot.getSize();
            used += parkingLot.getSize() - parkingLot.getAvailableSpaces();
            output += "\n|" + parkingLot.getId();
            output += "|" + parkingLot.getName();
            output += "|" + parkingLot.getSize() + "(个)";
            output += "|" + (parkingLot.getSize() - parkingLot.getAvailableSpaces()) + "(辆)";
            output += "|" + parkingLot.getAvailableSpaces() + "(个)" + "|";
        }
        left = total - used;
        output += "\n\n总车位：" + total + "(个)";
        output += "\n停车总量：" + used + "(辆)";
        output += "\n总剩余车位：" + left + "(个)";
        System.out.println(output);
    }

    public void outputAddParkingLotSuccessfully(){
        this.send("停车场添加成功！");
    }

    public void outputEnterParkingLotId(){
        this.send("请输入需要删除的被管理停车场ID:");
    }

    public void outputRemoveParkingLotFailed(String reason){
        this.send("停车场删除失败，原因：" + reason);
    }

    public void outputRemoveParkingLotSuccessfully(){
        this.send("停车场删除成功！");
    }

    public void outputAddFaild(){
        this.send("添加失败！请检查您的输入格式！");
    }





}
