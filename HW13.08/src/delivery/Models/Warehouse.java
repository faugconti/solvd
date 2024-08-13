package delivery.Models;

public class Warehouse {
    private String location;
    private int capacity; //m²

    public Warehouse(String location, int capacity) {
        this.location = location;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
