package com.thoughtworks.tdd;

import java.util.Scanner;

/**
 * @author Dylan Wei
 * @date 2018-07-16 10:35
 */
public class InputHandler {
    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String readNextCommand(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }


}
