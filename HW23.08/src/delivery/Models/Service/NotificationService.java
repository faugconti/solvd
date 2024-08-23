package delivery.Models.Service;

import delivery.enums.Message;
import delivery.interfaces.Notifiable;

public final class NotificationService {
    
    public static void sendNotification(Notifiable recipient,Message message){
        recipient.receiveNotification(message);
    }

}
