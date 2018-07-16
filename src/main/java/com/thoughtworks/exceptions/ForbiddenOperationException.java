package com.thoughtworks.exceptions;

/**
 * @author Dylan Wei
 * @date 2018-07-16 18:26
 */
public class ForbiddenOperationException extends RuntimeException{

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.out.println("操作被禁止！");
    }
}
