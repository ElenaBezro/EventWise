package bezro.payload;

import java.util.List;

public class UpdateVendorRequest {
    private List<String> services;

    public UpdateVendorRequest(List<String> services) {
        this.services = services;
    }

    public UpdateVendorRequest() {
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
