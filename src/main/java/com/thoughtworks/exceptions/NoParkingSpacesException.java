package com.thoughtworks.exceptions;

/**
 * @author Dylan Wei
 * @date 2018-07-13 17:02
 */
public class NoParkingSpacesException extends RuntimeException {
    private static final String caution = "车位已满，停车失败！";

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.out.println(caution);
    }
}
