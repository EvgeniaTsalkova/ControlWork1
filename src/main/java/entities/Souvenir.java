package entities;

import java.io.Serializable;

public class Souvenir implements Serializable {
    private String name;
    private int yearOfManufacture;
    private double price;
    private Manufacturer manufacturer;

    public Souvenir(String name, int yearOfManufacture, double price, Manufacturer manufacturer) {
        this.name = name;
        this.yearOfManufacture = yearOfManufacture;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public Souvenir() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Souvenir{" +
                "name = '" + name + '\'' +
                ", yearOfManufacture = " + yearOfManufacture +
                ", price = " + price +
                ", manufacturerName = " + manufacturer.getName() +
                ", manufacturerCountry = " + manufacturer.getCountry() +
                '}';
    }
}
