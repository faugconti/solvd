package delivery.Models.Order;

import java.util.ArrayList;
import java.util.List;

import delivery.Models.Vehicle;
import delivery.Models.Person.Customer;
import delivery.Models.Person.Employee;
import delivery.Models.Address;

public class DeliveryCompany {

    private List<Order> orders;
    private List<Customer> customers;
    private List<Vehicle> vehicles;
    private List<Employee> employees;
    private List<Address> addresses;

    public DeliveryCompany() {
        this.orders = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.addresses = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setVehicles(List<Vehicle> vehicles) {
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
        for (Vehicle vehicle : vehicles) {
            sb.append("- ").append(vehicle.getType()).append(" (").append(vehicle.getModel()).append(")\n");
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