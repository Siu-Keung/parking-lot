package com.thoughtworks.exceptions;

/**
 * @author Dylan Wei
 * @date 2018-07-13 17:14
 */
public class UnavailableCertificateException extends RuntimeException {
    private static final String caution = "取车凭证有误！";

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.out.println(caution);
    }
}
