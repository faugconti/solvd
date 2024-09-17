package delivery.interfaces;

import java.time.LocalDate;

public interface Schedulable {

    public void scheduleDelivery(LocalDate deliveryDate);
    public LocalDate getScheduledDate();

}
