package com.search.problem;

/**
 * Created by Sondre on 03.10.2014.
 */
public class Car {
    int number;
    Position position;
    int length;
    boolean isHorizontal;

    public Car(int number, Position position, int length, boolean isHorizontal) {
        this.number = number;
        this.position = position;
        this.length = length;
        this.isHorizontal = isHorizontal;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (isHorizontal != car.isHorizontal) return false;
        if (length != car.length) return false;
        if (position != null ? !position.equals(car.position) : car.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + length;
        result = 31 * result + (isHorizontal ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "number=" + number +
                ", position=" + position +
                ", length=" + length +
                ", isHorizontal=" + isHorizontal +
                '}';
    }
}
