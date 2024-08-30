package delivery.interfaces;

public interface Trackable {

    String getTrackingDetails();
    void addTrackingEvent(String location);

}