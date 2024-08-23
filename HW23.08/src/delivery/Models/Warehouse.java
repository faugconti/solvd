package delivery.Models;

public class Warehouse {
    private Address location;

    private final int capacity; // m²

    public Warehouse(Address location, int capacity) {
        this.location = location;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public Address getLocation() {
        return location;
    }

    // public void setCapacity(int capacity) {
    //     this.capacity = capacity;
    // }

    public void setLocation(Address location) {
        this.location = location;
    }

}
