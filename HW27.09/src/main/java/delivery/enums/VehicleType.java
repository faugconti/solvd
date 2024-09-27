package delivery.enums;

public enum VehicleType {
    TRUCK(400),
    BICYCLE(30),
    MOTORCYCLE(60);

    private final int maxLoadKg;

    VehicleType(int maxLoadKg) {
        this.maxLoadKg = maxLoadKg;
    }

    public int getMaxLoadKg() { return maxLoadKg; }
}
