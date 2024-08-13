package delivery;

import java.time.LocalDate;
import java.util.Date;

import delivery.Models.Warehouse;
import delivery.Models.Order.ExpressOrder;
import delivery.Models.Order.InternationalOrder;
import delivery.Models.Order.SubscriptionOrder;
import delivery.Models.Person.Customer;
import delivery.Models.Person.DeliveryPerson;
import delivery.Models.Person.Employee;
import delivery.Models.Person.Manager;
import delivery.Models.Person.StaffMember;
import delivery.enums.Payments;

public class App {
    public static void main(String[] args) throws Exception {

        // create 1 warehouse
        Warehouse headquarters = new Warehouse("Main street 50", 3000);

        // create 3 employee persons: 1 Manager, 1 driver and 1 staff

        Manager manager1 = new Manager("John", "John@delivery.com", "Bacon street 123", 5000,
                new Date(System.currentTimeMillis()), headquarters, 20);

        Employee StaffMember1 = new StaffMember("Jack", "Jack_HR@delivery.com", "Park Avenue 653", 2000,
                new Date(System.currentTimeMillis()), false, headquarters);

        Employee driver1 = new DeliveryPerson("Samuel", "samuel@delivery.com", "24th street 30", 3000,
                new Date(System.currentTimeMillis()), 4.0F, true);

        // create 1 person customer
        Customer customer1 = new Customer("Luois Volh", "luvolh@gmail.com", "Drive avenue 3200");
        customer1.setFavoritePaymentType(Payments.PAYPAL);

        InternationalOrder order1 = new InternationalOrder(1, customer1, new Date(System.currentTimeMillis()),
                customer1.getAddress(), " Eastern Rd 32, Nassau", "AR3201243BN", "Bahamas");

        ExpressOrder order2 = new ExpressOrder(2, customer1, new Date(System.currentTimeMillis()),
                customer1.getAddress(), "Lazy street 32", 5, 24);

        SubscriptionOrder order3 = new SubscriptionOrder(3, customer1, new Date(System.currentTimeMillis()), customer1.getAddress(),
        "Palm street 100", null, new Date(System.currentTimeMillis()),
                LocalDate.now().plusMonths(1), 1);

        customer1.listOrders();

    }
}
