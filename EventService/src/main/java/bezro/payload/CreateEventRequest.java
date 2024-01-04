package bezro.payload;

import java.time.LocalDateTime;
import java.util.List;

public class CreateEventRequest {
    private String eventName;
    private Long clientId;
    private Long vendorId;
    private LocalDateTime eventDateTime;

    public CreateEventRequest(String eventName, Long clientId, Long vendorId, LocalDateTime eventDateTime) {
        this.eventName = eventName;
        this.clientId = clientId;
        this.vendorId = vendorId;
        this.eventDateTime = eventDateTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }
}
