package delivery.interfaces;

import delivery.enums.Message;

public interface Notifiable {
    public void receiveNotification(Message message);
}
