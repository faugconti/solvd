package delivery.Models.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import delivery.Models.Vehicle;
import delivery.Models.Person.Customer;
import delivery.Models.Person.Employee;
import delivery.Models.Address;
import delivery.Models.MyLinkedList;

public class DeliveryCompany {

    private MyLinkedList<Order> orders;
    private Set<Customer> customers;
    private Map<Integer, Vehicle> vehicles;
    private List<Employee> employees;
    private List<Address> addresses;

    public DeliveryCompany() {
        this.orders = new MyLinkedList<>();
        this.customers = new HashSet<>();
        this.employees = new ArrayList<>();
        this.vehicles = new HashMap<>();
        this.addresses = new ArrayList<>();
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public MyLinkedList<Order> getOrders() {
        return orders;
    }

    public Map<Integer, Vehicle> getVehicles() {
        return vehicles;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setOrders(MyLinkedList<Order> orders) {
        this.orders = orders;
    }

    public void setVehicles(Map<Integer, Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DeliveryCompany Summary:\n");
        sb.append("------------------------\n");

        sb.append("Orders: ").append(orders.size()).append("\n");
        sb.append("Customers: ").append(customers.size()).append("\n");
        sb.append("Vehicles: ").append(vehicles.size()).append("\n");
        sb.append("Employees: ").append(employees.size()).append("\n");
        sb.append("Addresses: ").append(addresses.size()).append("\n");

        sb.append("\nEmployees:\n");
        for (Employee emp : employees) {
            sb.append("- ").append(emp.getName()).append(" (").append(emp.getEmployeeRole()).append(")\n");
        }

        sb.append("\nCustomers:\n");
        for (Customer cust : customers) {
            sb.append("- ").append(cust.getName()).append(" (").append(cust.getEmail()).append(")\n");
        }

        sb.append("\nVehicles:\n");
        for (Vehicle vehicle : vehicles.values()) {
            sb.append("- Type: ").append(vehicle.getType())
              .append(", Model: ").append(vehicle.getModel())
              .append("\n");
        }

        sb.append("\nLast 5 orders:\n");
        int start = Math.max(0, orders.size() - 5);
        for (int i = start; i < orders.size(); i++) {
            Order order = orders.get(i);
            sb.append("- Order #").append(order.getOrderID())
                    .append(" sender: ").append(order.getSender().getName())
                    .append(" (Status: ").append(order.status()).append(")\n");
        }

        return sb.toString();
    }

}