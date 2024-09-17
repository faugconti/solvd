package delivery.enums;

public enum Message {
    START_DELIVERY("Delivery has started."),
    END_DELIVERY("Delivery has been completed.");

    private final String message;

    Message(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getFormattedMessage(String orderID){
        return String.format("-Order %s -: %s",orderID,this.message);
    }
}
