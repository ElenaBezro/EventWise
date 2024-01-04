package bezro.service;

import bezro.payload.CreateVendorRequest;
import bezro.payload.UpdateVendorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import bezro.model.Vendor;
import bezro.repository.VendorRepository;

import java.util.Optional;

@Service
public class VendorService {
    private final VendorRepository vendorRepository;

    @Autowired
    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Vendor getVendorById(Integer id) {
        Optional<Vendor> vendor = vendorRepository.findById(id);
        if (vendor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return vendor.get();
    }

    public Boolean isAvailable(Integer id) {
        Optional<Vendor> vendor = vendorRepository.findById(id);
        if (vendor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return vendor.get().getAvailable();
    }

    public void createVendor(CreateVendorRequest createVendorRequest) {
        Vendor vendor = new Vendor(createVendorRequest.getName(),
                createVendorRequest.getAvailable(), createVendorRequest.getServices());
        vendorRepository.save(vendor);
    }

    public void addServices(UpdateVendorRequest updateVendorRequest, Integer id) {
        Vendor vendor = getVendorById(id);
        vendor.addServices(updateVendorRequest.getServices());
        vendorRepository.save(vendor);
    }
}
