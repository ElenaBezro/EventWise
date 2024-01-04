package bezro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import bezro.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
}
