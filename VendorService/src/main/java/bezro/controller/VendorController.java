package bezro.controller;

import bezro.payload.CreateVendorRequest;
import bezro.payload.UpdateVendorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import bezro.model.Vendor;
import bezro.service.VendorService;


@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    @GetMapping("/{id}")
    public Vendor findById(@PathVariable("id") Integer id) {
        return vendorService.getVendorById(id);
    }

    @GetMapping("/checkAvailability/{id}")
    public Boolean isAvailable(@PathVariable("id") Integer id) {
        return vendorService.isAvailable(id);
    }

    @PostMapping
    public void createVendor(@RequestBody CreateVendorRequest createVendorRequest) {
        vendorService.createVendor(createVendorRequest);
    }

    @PutMapping("/{id}")
    public void addServices(@RequestBody UpdateVendorRequest updateVendorRequest, @PathVariable("id") Integer id) {
        vendorService.addServices(updateVendorRequest, id);
    }
}
