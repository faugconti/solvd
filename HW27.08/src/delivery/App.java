package delivery;

import delivery.Models.*;
import delivery.Models.Order.*;
import delivery.Models.Person.*;
import delivery.Models.Service.DefaultDataService;
import delivery.Models.Service.DeliveryService;
import delivery.enums.PaymentType;
import delivery.enums.Role;
import delivery.enums.VehicleType;

import java.time.LocalDate;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
        private static final Logger logger = LogManager.getLogger(App.class);

        public static void main(String[] args) throws Exception {
                logger.info("Delivery Service application started");
                try {
                        DeliveryService.startProgram(DefaultDataService.generateDefaultCompany());
                } catch (Exception e) {
                        logger.error("Error occurred "+e.getMessage());
                }

                // Address warehouseAddr = new Address("USA", "00034", "CA", "Main street 50");

                // Address customer1Addr = new Address("USA", "94105", "SF", "Sunshine boulevard 300");
                // Address managerAddr = new Address("Canada", "G1A0A9", "QC", "Old Ontario 43A");
                // Address driverAddr = new Address("USA", "90047", "LA", "1404 W 105th St");
                // Address staffAddr = new Address("USA", "92648", "CA", "18202 Parktree Cir");

                // Address order1Addr = new Address("CN", "51000", "CQ", "Bao Shan Ba Cun 152hao 706shi");
                // Address order2Addr = new Address("USA", "94445", "LA", "5010 11th Ave, Crenshaw");
                // Address order3Addr = new Address("USA", "92648", "CA", "509 Esplanade, Redondo Beach");

                // // create 2 vehicles

                // Vehicle vehicle1 = new Vehicle(2020, "Peugeot Partner", VehicleType.TRUCK);
                // Vehicle vehicle2 = new Vehicle(2015, "SHIMANO", VehicleType.BICYCLE);

                // // create 1 warehouse
                // Warehouse headquarters = new Warehouse(warehouseAddr, 3000);

                // // create 3 employee persons: 1 Manager, 1 driver and 1 staff

                // Manager manager1 = new Manager("Eugene Levy", "eugeneL@delivery.com",
                // managerAddr, 5000,
                // new Date(System.currentTimeMillis()), headquarters, 20);

                // Employee StaffMember1 = new StaffMember("Jack", "Jack_HR@delivery.com",
                // staffAddr, 2000,
                // new Date(System.currentTimeMillis()), false, headquarters);

                // DeliveryPerson driver1 = new DeliveryPerson("Samuel Gimenez",
                //                 "samuel@delivery.com", driverAddr, 3000,
                //                 new Date(System.currentTimeMillis()), 4.0F, true, vehicle1);

                // DeliveryPerson driver2 = new DeliveryPerson("Pedro Gonzales",
                //                 "pedro@delivery.com", driverAddr, 2000,
                //                 new Date(System.currentTimeMillis()), 2.0F, true, vehicle2);

                // // create 1 person customer
                // Customer customer1 = new Customer("Luois Volh", "luvolh@gmail.com",
                //                 customer1Addr);
                // customer1.setFavoritePaymentType(PaymentType.PAYPAL);

                // InternationalOrder order1 = new InternationalOrder(1, customer1, new Date(System.currentTimeMillis()),
                //                 customer1.getAddress(), order1Addr, 900F, "US4302010CN", "China");

                // ExpressOrder order2 = new ExpressOrder(2, customer1, new Date(System.currentTimeMillis()),
                //                 customer1.getAddress(), order2Addr, 543F, 24, 5, driver1);

                // SubscriptionOrder order3 = new SubscriptionOrder(3, customer1, new Date(System.currentTimeMillis()),
                //                 customer1.getAddress(), order3Addr, 100F, LocalDate.now().plusWeeks(1),
                //                 new Date(System.currentTimeMillis()),
                //                 LocalDate.now().plusMonths(2), 2, driver2);

                // customer1.payOrder(order1);
                // customer1.payOrder(order2);
                // customer1.payOrder(order3);

                // order1.addTrackingEvent("India");
                // order1.addTrackingEvent("China");
                // System.out.println("Tracking history for Track Code " + order1.gettrackingNumber() + ": "
                //                 + order1.getTrackingDetails());

                // System.out.println();
                // order3.getScheduledDate();
                // order3.scheduleDelivery(LocalDate.now().plusDays(70));
                // order3.scheduleDelivery(LocalDate.now().plusDays(45));
                // order3.getScheduledDate();

                // System.out.println();
                // System.out.println("Order #" + order2.getOrderID() + " is delivered yet? " + order2.isDelivered());
                // order2.startDelivery();
                // System.out.println("Order #" + order2.getOrderID() + " status: " + order2.status());
                // driver1.completeDelivery();
                // System.out.println("Order #" + order2.getOrderID() + " is delivered yet? " + order2.isDelivered());

                // customer1.listOrders();

                // // System.out.println(customer1);
                // // System.out.println(manager1);
                // // System.out.println(driver1);
                // // System.out.println(StaffMember1);

                // // Employee emp1 = new Employee("John", "john@delivery.com", order3Addr, 10001,
                // // new Date(System.currentTimeMillis()), Role.DRIVER);
                // // Employee emp2 = new Employee("John", "john@delivery.com", order3Addr, 1000,
                // // new Date(System.currentTimeMillis()), Role.DRIVER);

                // // System.out.printf("%b %b \n",emp1.equals(emp2),emp2.equals(emp1));

        }

}
