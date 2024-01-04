package bezro.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Boolean isAvailable;
    private List<String> services;

    public Vendor(String name, Boolean isAvailable, List<String> services) {
        this.name = name;
        this.isAvailable = isAvailable;
        this.services = services;
    }

    public Vendor() {
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
    public void addServices(List<String> services) {
        this.services.addAll(services);
    }
}
