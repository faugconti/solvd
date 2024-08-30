package delivery.interfaces;


public interface Deliverable {
    void startDelivery();
    void completeDelivery();
    boolean isDelivered();

}