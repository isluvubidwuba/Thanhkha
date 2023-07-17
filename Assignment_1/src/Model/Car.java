/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Acer
 */
public class Car implements Comparable<Car> {

    //Car ID, brand ID, color, frame ID, engine ID
    private String carID;
    private Brand brand;
    private String color;
    private String framID;
    private String engineID;

    public Car(String carID, Brand brand, String color, String framID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.framID = framID;
        this.engineID = engineID;
    }

    public Car() {
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrandID() {
        return brand;
    }

    public void setBrandID(Brand brandID) {
        this.brand = brandID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFramID() {
        return framID;
    }

    public void setFramID(String framID) {
        this.framID = framID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    @Override
    public String toString() {
        // format < carID, brand.brandID, color, frameID, engineID>
        return this.carID + ", " + this.brand.getBrandID()+ ", " + this.color + ", " + this.framID + ", " + this.engineID;
    }


    public String screenString() {
        // < brand, “\n”, car_ID, color, frameID, engineID>
        return this.brand + " \n" + carID + ", " + color + ", " + framID + ", " + engineID;
    }

    @Override
    public int compareTo(Car c) {
        int d = this.brand.getBrandName().compareTo(c.brand.getBrandName());
        if (d != 0)
            return d;
        return this.carID.compareTo(c.carID);
    }

}
