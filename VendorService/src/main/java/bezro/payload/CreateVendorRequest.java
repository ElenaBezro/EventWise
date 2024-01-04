package bezro.payload;

import java.util.List;

public class CreateVendorRequest {
    private String name;
    private Boolean isAvailable;
    private List<String> services;

    public CreateVendorRequest(String name, Boolean isAvailable, List<String> services) {
        this.name = name;
        this.isAvailable = isAvailable;
        this.services = services;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
