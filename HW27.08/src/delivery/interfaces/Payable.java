package delivery.interfaces;

import delivery.Models.Order.Order;

public interface Payable {
    public void payOrder(Order order);
}
