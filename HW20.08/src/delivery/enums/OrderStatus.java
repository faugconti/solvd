package delivery.enums;

public enum OrderStatus {
    DRAFT,             // once created
    COMPLETED,         // once completed
    DELAYED,           // once delayed
    INPROGRESS,        // once accepted by carrier
    READY              // once payed
}
