package com.thoughtworks.tdd;

import com.thoughtworks.exceptions.IllegalCommandException;

import java.util.Scanner;

public class InteractionHandler {

    public int showMainScreenAndGetChoice(){
        System.out.printf("1. 停车\n2. 取车\n请输入您要进行的操作：");
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

}
