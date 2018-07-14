package com.thoughtworks.tdd;

import com.thoughtworks.exceptions.IllegalCommandException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Scanner;

public class InteractionHandler {

    public int showMainScreenAndGetChoice(){
        System.out.print("1. 停车\n2. 取车\n请输入您要进行的操作：");
        Scanner input = new Scanner(System.in);
        int choice;
        try {
            choice = input.nextInt();
        }catch (Exception e){
            throw new IllegalCommandException();
        }
        if(choice != 1 && choice != 2)
            throw new IllegalCommandException();
        return choice;
    }

    public String displayPromptAndGetCarNum() {
        System.out.print("请输入车牌号：");
        Scanner input = new Scanner(System.in);
        String carNum = input.nextLine();
        return carNum;
    }

    public void displayParkSuccessfully(String certificateNo) {
        System.out.printf("停车成功，您的小票是：\n%s\n", certificateNo);
    }

    public String displayPromptAndGetCertificateNo() {
        System.out.print("请输入小票编号：");
        Scanner input = new Scanner(System.in);
        String certificateNo = input.nextLine();
        return certificateNo;
    }

    public void displayUnparkSuccessfully(String carNo) {
        System.out.printf("车已取出，您的车牌号是: %s\n", carNo);
    }

    public void alertIllegalCommand(){
        System.out.println("非法指令，请查证后再输");
    }

    public void alertAllParkingLotIsFull(){
        System.out.println("车已停满，请晚点再来");
    }

    public void alertNotAvailableCertificate(){
        System.out.println("非法小票，无法取出车，请查证后再输");
    }
}
