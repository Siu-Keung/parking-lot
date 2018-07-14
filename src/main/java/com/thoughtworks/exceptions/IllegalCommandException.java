package com.thoughtworks.exceptions;

public class IllegalCommandException extends RuntimeException{
    private String caution = "无效指令！请检查后再输入！";

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.out.println(caution);
    }
}
