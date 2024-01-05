package bezro.payload;

import java.time.LocalDateTime;
import java.util.List;

public class CreateEventRequest {
    private String eventName;
    private Integer clientId;
    private Integer vendorId;
    private LocalDateTime eventDateTime;

    public CreateEventRequest(String eventName, Integer clientId, Integer vendorId, LocalDateTime eventDateTime) {
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

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }
}
