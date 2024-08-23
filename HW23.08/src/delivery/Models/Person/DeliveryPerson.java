package delivery.Models.Person;

import java.util.Date;

import delivery.Models.Address;
import delivery.Models.Vehicle;
import delivery.Models.Order.Order;
import delivery.enums.Message;
import delivery.enums.Role;
import delivery.interfaces.Deliverable;
import delivery.interfaces.Notifiable;

public class DeliveryPerson extends Employee implements Notifiable, Deliverable {
    private float rating;
    private Boolean isAvailable;
    private Order currentOrder;
    private Vehicle currenVehicle;

    public DeliveryPerson(String name, String email, Address address, double salary, Date hireDate, float rating,
            Boolean isAvailable, Vehicle vehicle) {
        super(name, email, address, salary, hireDate, Role.DRIVER);
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.currentOrder = null;
        this.currenVehicle = vehicle;
    }

    public DeliveryPerson(Employee employee, float rating, Boolean isAvailable, Vehicle vehicle) {
        super(employee.getName(), employee.getEmail(), employee.getAddress(), employee.getSalary(),
                employee.getHireDate(), Role.DRIVER);
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.currentOrder = null;
        this.currenVehicle = vehicle;
    };

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Boolean availability() {
        return isAvailable;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setCurrenVehicle(Vehicle currenVehicle) {
        this.currenVehicle = currenVehicle;
    }

    public Vehicle getCurrenVehicle() {
        return currenVehicle;
    }

    public float getRating() {
        return rating;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    @Override
    public void receiveNotification(Message message) {
        if (message == Message.START_DELIVERY) {
            this.startDelivery();
        }
    }

    @Override
    public void startDelivery() {
        System.out.println(
                "[Carrier: " + super.getName() + "]Order " + this.currentOrder.getOrderID() + " delivery started.");
        this.isAvailable = false;

    }

    @Override
    public void completeDelivery() {
        System.out.println(
                "[Carrier: " + super.getName() + "]Order " + this.currentOrder.getOrderID() + " delivery ended.");
        this.currentOrder.completeDelivery();
        this.isAvailable = true;
        this.currentOrder = null;
    }

    @Override
    public boolean isDelivered() {
        return this.currentOrder == null;
    }

}
