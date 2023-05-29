package tedorm.app.application.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.address.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}