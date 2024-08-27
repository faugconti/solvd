package delivery.Models.Order;

import java.io.FileWriter;
import java.io.IOException;

import delivery.exceptions.OrderWriteException;

public class OrderWriter implements AutoCloseable {
    private FileWriter writer;

    public OrderWriter(String fileName) throws OrderWriteException {
        try {
            this.writer = new FileWriter(fileName, true); // true for append mode¿?
        } catch (IOException exc) {
            throw new OrderWriteException("Error trying to write to file", exc);
        }
    }

    public void writeOrder(Order order) throws OrderWriteException {
        try {

            writer.write("Order ID: " + order.getOrderID() + "\n");
            writer.write("Customer: " + order.getSender().getName() + "\n");
            writer.write("Status: " + order.status() + "\n");
            writer.write("Total: $" + order.getPrice() + "\n");
            writer.write("------------------------\n");
        } catch (IOException exc) {
            throw new OrderWriteException("Error trying to write to file", exc);
        }
    }

    @Override
    public void close() throws OrderWriteException {
        if (writer != null) {
            try {

                writer.close();
            } catch (IOException exc) {
                throw new OrderWriteException("Failed to close writer to file", exc);
            }
        }
    }
}