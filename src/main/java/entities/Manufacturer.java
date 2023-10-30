package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Manufacturer implements Serializable {
    private String name;
    private String country;
    private List<Souvenir> souvenirList;

    public Manufacturer(String name, String country, List<Souvenir> souvenirList) {
        this.name = name;
        this.country = country;
        this.souvenirList = souvenirList;
    }

    public Manufacturer() {}

    public void printInfo() {
        System.out.println("Manufacturer's name:" + name + ", Manufacturer's country: " + country);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Souvenir> getSouvenirList() {
        return souvenirList;
    }

    public void setSouvenirList(List<Souvenir> souvenirList) {
        this.souvenirList = souvenirList;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "name = '" + name + '\'' +
                ", country = '" + country + '\'' +
                ", souvenirList = " + souvenirList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return name.equals(that.name) && country.equals(that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}
