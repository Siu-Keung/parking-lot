package com.thoughtworks.tdd;

/**
 * @author Dylan Wei
 * @date 2018-07-11 14:12
 */
public class Certificate {
    private String id;
    private Car car;

    public Certificate(String id) {
        this.id = id;
    }

    public Certificate(String id, Car car) {
        this.id = id;
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id='" + id + '\'' +
                ", car=" + car +
                '}';
    }
}
