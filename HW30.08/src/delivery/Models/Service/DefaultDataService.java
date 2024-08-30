package delivery.Models.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import delivery.Models.Address;
import delivery.Models.MyLinkedList;
import delivery.Models.Vehicle;
import delivery.Models.Warehouse;
import delivery.Models.Order.*;
import delivery.Models.Person.*;
import delivery.enums.PaymentType;
import delivery.enums.VehicleType;

public class DefaultDataService {
        public static DeliveryCompany generateDefaultCompany() {

                DeliveryCompany deliveryCompany = new DeliveryCompany();

                List<Address> addresses = new LinkedList<>();
                Map<Integer, Vehicle> vehicles = new HashMap<>();
                List<Warehouse> warehouses = new LinkedList<>();
                List<Employee> employees = new LinkedList<>();
                Set<Customer> customers = new HashSet<>();
                MyLinkedList<Order> orders = new MyLinkedList<>();

                Address warehouseAddr = new Address("USA", "00034", "CA", "Main street 50");
                Address customer1Addr = new Address("USA", "94105", "SF", "Sunshine boulevard 300");
                Address managerAddr = new Address("Canada", "G1A0A9", "QC", "Old Ontario 43A");
                Address driverAddr = new Address("USA", "90047", "LA", "1404 W 105th St");
                Address staffAddr = new Address("USA", "92648", "CA", "18202 Parktree Cir");

                Address order1Addr = new Address("CN", "51000", "CQ", "Bao Shan Ba Cun 152hao 706shi");
                Address order2Addr = new Address("USA", "94445", "LA", "5010 11th Ave, Crenshaw");
                Address order3Addr = new Address("USA", "92648", "CA", "509 Esplanade, Redondo Beach");

                // add addresses to list
                Collections.addAll(addresses, warehouseAddr, customer1Addr, managerAddr, driverAddr, staffAddr,
                                order1Addr,
                                order2Addr, order3Addr);

                Vehicle vehicle1 = new Vehicle(2020, "Peugeot Partner", VehicleType.TRUCK);
                Vehicle vehicle2 = new Vehicle(2015, "SHIMANO", VehicleType.BICYCLE);

                // add vehicles to list if using List
                // Collections.addAll(vehicles, vehicle1, vehicle2);

                // add vehicles to map
                vehicles.put(0, vehicle1);
                vehicles.put(1, vehicle2);

                // add warehouse to list
                Warehouse headquarters = new Warehouse(warehouseAddr, 3000);
                Collections.addAll(warehouses, headquarters);

                Manager manager1 = new Manager("Eugene Levy", "eugeneL@delivery.com",
                                managerAddr, 5000,
                                new Date(System.currentTimeMillis()), headquarters, 20);

                Employee staffMember1 = new StaffMember("Jack", "Jack_HR@delivery.com",
                                staffAddr, 2000,
                                new Date(System.currentTimeMillis()), false, headquarters);

                DeliveryPerson driver1 = new DeliveryPerson("Samuel Gimenez",
                                "samuel@delivery.com", driverAddr, 3000,
                                new Date(System.currentTimeMillis()), 4.0F, true, vehicle1);

                DeliveryPerson driver2 = new DeliveryPerson("Pedro Gonzales",
                                "pedro@delivery.com", driverAddr, 2000,
                                new Date(System.currentTimeMillis()), 2.0F, true, vehicle2);

                // add employees to list
                Collections.addAll(employees, manager1, staffMember1, driver1, driver2);

                Customer customer1 = new Customer("Luois Volh", "luvolh@gmail.com",
                                customer1Addr);
                customer1.setFavoritePaymentType(PaymentType.PAYPAL);

                // add customer to list
                Collections.addAll(customers, customer1);

                InternationalOrder order1 = new InternationalOrder(1, customer1, new Date(System.currentTimeMillis()),
                                customer1.getAddress(), order1Addr, 900F, "US4302010CN", "China");

                ExpressOrder order2 = new ExpressOrder(2, customer1, new Date(System.currentTimeMillis()),
                                customer1.getAddress(), order2Addr, 543F, 24, 5, driver1);

                SubscriptionOrder order3 = new SubscriptionOrder(3, customer1, new Date(System.currentTimeMillis()),
                                customer1.getAddress(), order3Addr, 100F, LocalDate.now().plusWeeks(1),
                                new Date(System.currentTimeMillis()),
                                LocalDate.now().plusMonths(2), 2, driver2);

                customer1.payOrder(order1);
                customer1.payOrder(order2);
                customer1.payOrder(order3);

                order1.addTrackingEvent("India");
                order1.addTrackingEvent("China");

                System.out.println("Tracking history for Track Code " + order1.gettrackingNumber() + ": "
                                + order1.getTrackingDetails());

                // add orders to list if using a List
                // Collections.addAll(orders, order1, order2, order3);

                System.out.println();
                order3.getScheduledDate();
                order3.scheduleDelivery(LocalDate.now().plusDays(70));
                order3.scheduleDelivery(LocalDate.now().plusDays(45));
                order3.scheduleDelivery(LocalDate.now().plusDays(2));
                order3.getScheduledDate();

                orders.add(order1);
                orders.add(order2);
                orders.add(order3);

                deliveryCompany.setAddresses(addresses);
                deliveryCompany.setCustomers(customers);
                deliveryCompany.setEmployees(employees);
                deliveryCompany.setVehicles(vehicles);
                deliveryCompany.setOrders(orders);

                return deliveryCompany;
        }
}
