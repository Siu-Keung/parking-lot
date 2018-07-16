package com.thoughtworks.domain;

/**
 * @author Dylan Wei
 * @date 2018-07-11 14:11
 */
public class Car {
    private String carNumber;

    @Override
    public String toString() {
        return "Car{" +
                "carNumber='" + carNumber + '\'' +
                '}';
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Car(String carNumber) {

        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Car)
            return ((Car) obj).getCarNumber().equals(this.carNumber);
        else
            return false;
    }
}
