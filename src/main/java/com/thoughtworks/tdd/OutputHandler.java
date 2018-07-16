package com.thoughtworks.tdd;

import sun.security.krb5.internal.APRep;

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

}
