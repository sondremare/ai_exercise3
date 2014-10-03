package com.search.problem;

import com.search.State;

import java.util.ArrayList;

/**
 * Created by Sondre on 27.09.2014.
 */

/** Class representing the state of certain 2d grid problems */
public class RushHourGridMap extends State {

    private ArrayList<Car> cars;
    private int height = 6;
    private int width = 6;
    private Position goal = new Position(4,2);

    public RushHourGridMap(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public RushHourGridMap(RushHourGridMap gridMap) {
        ArrayList<Car> carCopy = new ArrayList<Car>();
        for (Car car : gridMap.getCars()) {
            Position newPosition = new Position(car.getPosition().getX(), car.getPosition().getY());
            Car newCar = new Car(car.getNumber(),newPosition,car.getLength(),car.isHorizontal);
            carCopy.add(newCar);
        }
        this.cars = carCopy;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public boolean[][] findOccupiedSpots(Car forCar) {
        boolean[][] occupied = new boolean[height][width];
        for (Car car : cars) {
            if (!car.equals(forCar)) {
                if (car.isHorizontal()) {
                    int length = car.getLength();
                    for (int i = 0; i<length; i++) {
                        occupied[car.getPosition().getX()+i][car.getPosition().getY()] = true;
                    }
                } else {
                    int height = car.getLength();
                    for (int i = 0; i<height; i++) {
                        occupied[car.getPosition().getX()][car.getPosition().getY()+i] = true;
                    }
                }
            }
        }
        return occupied;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Position getGoal() {
        return goal;
    }

    public void setGoal(Position goal) {
        this.goal = goal;
    }

    public boolean isBlocked(Position position, Car car) {
        boolean[][] blocked = findOccupiedSpots(car);
        return (blocked[position.getX()][position.getY()]);
    }

    public boolean isPositionWithinBounds(Car car, Position position) {
        return (position.getX() >= 0 && position.getX() < this.height
                && position.getY() >= 0 && position.getY() < this.width);
    }

    public boolean isValidPosition(Car car, Position position) {
        return isPositionWithinBounds(car, position) && !isBlocked(position, car);
    }

    public void move(Car currentCar, Position position) {
        for (Car car : cars) {
            if (currentCar.equals(car)){
                car.setPosition(position);
            }
        }
    }

    public float getGCost() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RushHourGridMap)) return false;

        RushHourGridMap that = (RushHourGridMap) o;

        if (height != that.height) return false;
        if (width != that.width) return false;
        if (!cars.equals(that.cars)) return false;
        if (!goal.equals(that.goal)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cars.hashCode();
        result = 31 * result + height;
        result = 31 * result + width;
        result = 31 * result + goal.hashCode();
        return result;
    }
}
