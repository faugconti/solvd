package delivery.Models;

import delivery.enums.VehicleType;

public final class Vehicle {
    private int year;
    private String model;
    private VehicleType type;

    public Vehicle(int year, String model, VehicleType type) {
        this.year = year;
        this.model = model;
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public VehicleType getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
